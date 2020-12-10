package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.CounterAgent;
import uniloftsky.springframework.asamp.services.repositories.CounterAgentRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CounterAgentServiceImpl implements CounterAgentService {

    private final CounterAgentRepository counterAgentRepository;

    public CounterAgentServiceImpl(CounterAgentRepository counterAgentRepository) {
        this.counterAgentRepository = counterAgentRepository;
    }

    @Override
    public Set<CounterAgent> findAll() {
        Set<CounterAgent> agents = new HashSet<>();
        counterAgentRepository.findAll().iterator().forEachRemaining(agents::add);
        return agents;
    }

    @Override
    public CounterAgent findById(Long id) {
        Optional<CounterAgent> counterAgentOptional = counterAgentRepository.findById(id);
        if(counterAgentOptional.isEmpty()) {
            throw new RuntimeException("Expected counter agent not found!");
        }
        return counterAgentOptional.get();
    }

    @Override
    public void delete(CounterAgent counterAgent) {
        counterAgentRepository.delete(counterAgent);
    }

    @Override
    public CounterAgent save(CounterAgent counterAgent) {
        return counterAgentRepository.save(counterAgent);
    }
}
