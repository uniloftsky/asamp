package uniloftsky.springframework.asamp.services.repositories;

import org.springframework.data.repository.CrudRepository;
import uniloftsky.springframework.asamp.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
