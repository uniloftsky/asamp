package uniloftsky.springframework.asamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uniloftsky.springframework.asamp.comparators.CounterAgentAscComparatorById;
import uniloftsky.springframework.asamp.model.CounterAgent;
import uniloftsky.springframework.asamp.services.CounterAgentService;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class IndexController {

    private final CounterAgentService counterAgentService;

    public IndexController(CounterAgentService counterAgentService) {
        this.counterAgentService = counterAgentService;
    }

    @GetMapping({"", "/index", "index"})
    public String getIndexPage() {
        return "admin-panel/index";
    }

    @ModelAttribute("agents")
    public Set<CounterAgent> getAgentsList(Model model) {
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
        return "admin-panel/edit-page";
    }

    @PostMapping("editAgent")
    public String editAgentProcess(@ModelAttribute CounterAgent agent) {
        counterAgentService.save(agent);
        return "redirect:/index";
    }

}
