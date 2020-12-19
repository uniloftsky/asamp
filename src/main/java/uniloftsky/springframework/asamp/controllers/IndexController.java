package uniloftsky.springframework.asamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uniloftsky.springframework.asamp.comparators.*;
import uniloftsky.springframework.asamp.model.*;
import uniloftsky.springframework.asamp.services.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class IndexController {

    private final CounterAgentService counterAgentService;
    private final ItemNameService itemNameService;
    private final ItemService itemService;
    private final ItemTypeService itemTypeService;
    private final ItemAddService itemAddService;
    private final ItemRemoveService itemRemoveService;

    public IndexController(CounterAgentService counterAgentService, ItemNameService itemNameService, ItemService itemService, ItemTypeService itemTypeService, ItemAddService itemAddService, ItemRemoveService itemRemoveService) {
        this.counterAgentService = counterAgentService;
        this.itemNameService = itemNameService;
        this.itemService = itemService;
        this.itemTypeService = itemTypeService;
        this.itemAddService = itemAddService;
        this.itemRemoveService = itemRemoveService;
    }

    private DateTimeFormatter formatter;

    @GetMapping({"", "/index", "index"})
    public String getIndexPage(Model model) {
        model.addAttribute("agent", new CounterAgent());
        return "admin-panel/index";
    }

    @GetMapping({"items", "/items"})
    public String getItemsPage() {
        return "admin-panel/items-page";
    }

    @GetMapping({"adds", "/adds"})
    public String getAddsPage(Model model) {
        model.addAttribute("itemAdd", new ItemAdd());
        return "admin-panel/adds-page";
    }

    @GetMapping({"removes", "/removes"})
    public String getRemovePage(Model model) {
        model.addAttribute("itemRemove", new ItemRemove());
        return "admin-panel/removes-page";
    }

    @ModelAttribute("itemNames")
    public Set<ItemName> getItemNamesList() {
        Comparator<ItemName> comparator = new ItemNameAscComparatorById();
        TreeSet<ItemName> itemNames = new TreeSet<>(comparator);
        itemNameService.findAll().iterator().forEachRemaining(itemNames::add);
        return itemNames;
    }

    @ModelAttribute("itemTypes")
    public Set<ItemType> getItemTypesList() {
        Comparator<ItemType> comparator = new ItemTypeAscComparatorById();
        TreeSet<ItemType> itemTypes = new TreeSet<>(comparator);
        itemTypeService.findAll().iterator().forEachRemaining(itemTypes::add);
        return itemTypes;
    }

    @ModelAttribute("itemAdds")
    public Set<ItemAdd> getItemAddsList() {
        Comparator<ItemAdd> comparator = new ItemAddsAscComparatorById();
        TreeSet<ItemAdd> itemAdds = new TreeSet<>(comparator);
        itemAddService.findAll().iterator().forEachRemaining(itemAdds::add);
        return itemAdds;
    }

    @ModelAttribute("itemRemoves")
    public Set<ItemRemove> getItemRemovesList() {
        Comparator<ItemRemove> comparator = new ItemRemovesAscComparatorById();
        TreeSet<ItemRemove> itemRemoves = new TreeSet<>(comparator);
        itemRemoveService.findAll().iterator().forEachRemaining(itemRemoves::add);
        return itemRemoves;
    }

    @ModelAttribute("items")
    public Set<Item> getItemsList() {
        Comparator<Item> comparator = new ItemAscComparatorById();
        TreeSet<Item> items = new TreeSet<>(comparator);
        itemService.findAll().iterator().forEachRemaining(items::add);
        return items;
    }

    @ModelAttribute("currentDate")
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @GetMapping("editItem")
    public String editItemInit(@RequestParam("id") String id, Model model) {
        Item item = itemService.findById(Long.valueOf(id));
        model.addAttribute("item", item);
        return "admin-panel/edit-item";
    }

    @PostMapping("editItem")
    public String editItemProcess(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/edit-item";
        }
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("deleteItem")
    public String deleteItem(@RequestParam("id") String id) {
        itemService.delete(itemService.findById(Long.valueOf(id)));
        return "redirect:/items";
    }

    @ModelAttribute("agents")
    public Set<CounterAgent> getAgentsList(Model model) {
        Comparator<CounterAgent> comparator = new CounterAgentAscComparatorById();
        TreeSet<CounterAgent> agents = new TreeSet<>(comparator);
        counterAgentService.findAll().iterator().forEachRemaining(agents::add);
        return agents;
    }

    @PostMapping("agentAdd")
    public String agentAdd(@Valid @ModelAttribute("agent") CounterAgent counterAgent, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/index";
        }
        counterAgentService.save(counterAgent);
        return "redirect:/index";
    }

    @GetMapping("deleteAgent")
    public String deleteAgent(@RequestParam("id") String id) {
        counterAgentService.delete(counterAgentService.findById(Long.valueOf(id)));
        return "redirect:/index";
    }

    @GetMapping("editAgent")
    public String editAgentInit(@RequestParam("id") String id, Model model) {
        CounterAgent agent = counterAgentService.findById(Long.valueOf(id));
        model.addAttribute("agent", agent);
        return "admin-panel/edit-agent";
    }

    @PostMapping("editAgent")
    public String editAgentProcess(@Valid @ModelAttribute("agent") CounterAgent agent, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin-panel/edit-agent";
        }
        counterAgentService.save(agent);
        return "redirect:/index";
    }

    @PostMapping("itemTypeAdd")
    public String itemTypeAdd(@RequestParam("typeName") String typeName, @RequestParam("itemId") String itemId, Model model) {
        ItemType itemType = new ItemType();
        itemType.setTypeName(typeName);
        itemTypeService.save(itemType);
        return "redirect:/editItem?id=" + itemId;
    }

    @GetMapping("editItemAdd")
    public String editItemAddInit(@RequestParam("id") String itemAddId, Model model) {
        ItemAdd itemAdd = itemAddService.findById(Long.valueOf(itemAddId));
        model.addAttribute("itemAdd", itemAdd);
        return "admin-panel/edit-itemAdd";
    }

    @PostMapping("editItemAdd")
    public String editItemAddProcess(@Valid @ModelAttribute("itemAdd") ItemAdd itemAdd, BindingResult bindingResult, @RequestParam("dateField") String date, Model model) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/edit-itemAdd";
        }
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateAdd = LocalDate.parse(date, formatter);
        itemAdd.setDate(dateAdd);
        itemAddService.save(itemAdd);
        return "redirect:/adds";
    }

    @PostMapping("itemAddCreate")
    public String itemAddCreate(@Valid @ModelAttribute("itemAdd") ItemAdd itemAdd, BindingResult bindingResult, @RequestParam("dateField") String date) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/adds-page";
        }
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateAdd = LocalDate.parse(date, formatter);
        itemAdd.setDate(dateAdd);
        itemAddService.save(itemAdd, itemAdd.getItemType().getId());
        return "redirect:/adds";
    }

    @GetMapping("editItemRemove")
    public String editItemRemoveInit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("itemRemove", itemRemoveService.findById(id));
        return "admin-panel/edit-itemRemove";
    }

    @PostMapping("editItemRemove")
    public String editItemRemoveProcess(@Valid @ModelAttribute("itemRemove") ItemRemove itemRemove, BindingResult bindingResult, @RequestParam("dateField") String date) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/edit-itemRemove";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateAdd = LocalDate.parse(date, formatter);
        itemRemove.setDate(dateAdd);
        itemRemoveService.save(itemRemove);
        return "redirect:/removes";
    }

    @PostMapping("itemRemoveCreate")
    public String itemRemoveCreate(@Valid @ModelAttribute("itemRemove") ItemRemove itemRemove, BindingResult bindingResult, @RequestParam("dateField") String date, Model model) {
        if(bindingResult.hasErrors()) {
            return "admin-panel/removes-page";
        }
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateAdd = LocalDate.parse(date, formatter);
        itemRemove.setDate(dateAdd);
        if (itemRemoveService.save(itemRemove, itemRemove.getItemType().getId()) == null) {
            model.addAttribute("factCount", itemService.findByItemType_TypeName(itemTypeService.findById(itemRemove.getItemType().getId()).getTypeName()).getCount());
            model.addAttribute("removeCount", itemRemove.getCount());
            return "admin-panel/not-enough-items";
        } else {
            itemRemoveService.save(itemRemove, itemRemove.getItemType().getId());
            return "redirect:/removes";

        }
    }

    @GetMapping("printItemAdds")
    public String printItemAdds() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss");
        File uploadPath = new File("reports");
        if(!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        String fileName = uploadPath + "/itemAddsReport-" + LocalDateTime.now().format(formatter) + ".xlsx";
        itemAddService.write(fileName);
        return "admin-panel/success-print";
    }

    @GetMapping("printItemRemoves")
    public String printItemRemoves() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss");
        File uploadPath = new File("reports");
        if(!uploadPath.exists()) {
            uploadPath.mkdir();
        }
        String fileName = uploadPath + "/itemRemovesReport-" + LocalDateTime.now().format(formatter) + ".xlsx";
        itemRemoveService.write(fileName);
        return "admin-panel/success-print";
    }

}
