package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.CounterAgent;

import java.util.Set;

public interface CounterAgentService {

    Set<CounterAgent> findAll();
    CounterAgent findById(Long id);
    void delete(CounterAgent counterAgent);
    CounterAgent save(CounterAgent counterAgent);

}
