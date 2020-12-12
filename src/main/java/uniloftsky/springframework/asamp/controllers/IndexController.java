package uniloftsky.springframework.asamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uniloftsky.springframework.asamp.comparators.CounterAgentAscComparatorById;
import uniloftsky.springframework.asamp.comparators.ItemAscComparatorById;
import uniloftsky.springframework.asamp.comparators.ItemNameAscComparatorById;
import uniloftsky.springframework.asamp.comparators.ItemTypeAscComparatorById;
import uniloftsky.springframework.asamp.model.CounterAgent;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.model.ItemName;
import uniloftsky.springframework.asamp.model.ItemType;
import uniloftsky.springframework.asamp.services.CounterAgentService;
import uniloftsky.springframework.asamp.services.ItemNameService;
import uniloftsky.springframework.asamp.services.ItemService;
import uniloftsky.springframework.asamp.services.ItemTypeService;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class IndexController {

    private final CounterAgentService counterAgentService;
    private final ItemNameService itemNameService;
    private final ItemService itemService;
    private final ItemTypeService itemTypeService;

    public IndexController(CounterAgentService counterAgentService, ItemNameService itemNameService, ItemService itemService, ItemTypeService itemTypeService) {
        this.counterAgentService = counterAgentService;
        this.itemNameService = itemNameService;
        this.itemService = itemService;
        this.itemTypeService = itemTypeService;
    }

    @GetMapping({"", "/index", "index"})
    public String getIndexPage() {
        return "admin-panel/index";
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

    @GetMapping("deleteItem")
    public String deleteItem(@RequestParam("id") String id) {
        itemService.delete(itemService.findById(Long.valueOf(id)));
        return "redirect:/index";
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
        return "edit-agent";
    }

    @PostMapping("editAgent")
    public String editAgentProcess(@ModelAttribute CounterAgent agent) {
        counterAgentService.save(agent);
        return "redirect:/index";
    }

}
