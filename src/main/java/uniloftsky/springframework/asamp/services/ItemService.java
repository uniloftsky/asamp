package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.Item;

import java.util.Set;

public interface ItemService {

    Set<Item> findAll();
    Item findById(Long id);
    void delete(Item item);
    Item save(Item item);

}
