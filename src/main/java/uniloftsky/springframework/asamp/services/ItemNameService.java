package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.ItemName;

import java.util.Set;

public interface ItemNameService {

    Set<ItemName> findAll();
    ItemName findById(Long id);
    void delete(ItemName itemType);
    ItemName save(ItemName itemType);

}
