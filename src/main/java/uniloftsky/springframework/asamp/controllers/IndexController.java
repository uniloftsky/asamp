package uniloftsky.springframework.asamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uniloftsky.springframework.asamp.comparators.*;
import uniloftsky.springframework.asamp.model.*;
import uniloftsky.springframework.asamp.services.*;

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

    @GetMapping({"", "/index", "index"})
    public String getIndexPage() {
        return "admin-panel/index";
    }

    @GetMapping({"items", "/items"})
    public String getItemsPage() {
        return "admin-panel/items-page";
    }

    @GetMapping({"adds", "/adds"})
    public String getAddsPage() {
        return "admin-panel/adds-page";
    }

    @GetMapping({"removes", "/removes"})
    public String getRemovePage() {
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

    @GetMapping("editItem")
    public String editItemInit(@RequestParam("id") String id, Model model) {
        Item item = itemService.findById(Long.valueOf(id));
        model.addAttribute("item", item);
        return "admin-panel/edit-item";
    }

    @PostMapping("editItem")
    public String editItemProcess(@ModelAttribute("item") Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("deleteItem")
    public String deleteItem(@RequestParam("id") String id) {
        itemService.delete(itemService.findById(Long.valueOf(id)));
        return "redirect:/items";
    }

    @ModelAttribute("agents")
    public Set<CounterAgent> getAgentsList() {
        Comparator<CounterAgent> comparator = new CounterAgentAscComparatorById();
        TreeSet<CounterAgent> agents = new TreeSet<>(comparator);
        counterAgentService.findAll().iterator().forEachRemaining(agents::add);
        return agents;
    }

    @PostMapping("agentAdd")
    public String agentAdd(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("middleName") String middleName, @RequestParam("phone") String phone, @RequestParam("email") String email, Model model) {
        CounterAgent counterAgent = new CounterAgent();
        counterAgent.setFirstName(firstName);
        counterAgent.setLastName(lastName);
        counterAgent.setMiddleName(middleName);
        counterAgent.setPhone(phone);
        counterAgent.setEmail(email);
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
    public String editAgentProcess(@ModelAttribute CounterAgent agent) {
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

}
