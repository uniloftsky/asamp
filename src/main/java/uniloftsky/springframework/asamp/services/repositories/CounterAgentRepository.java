package uniloftsky.springframework.asamp.services.repositories;

import org.springframework.data.repository.CrudRepository;
import uniloftsky.springframework.asamp.model.CounterAgent;

public interface CounterAgentRepository extends CrudRepository<CounterAgent, Long> {
}
