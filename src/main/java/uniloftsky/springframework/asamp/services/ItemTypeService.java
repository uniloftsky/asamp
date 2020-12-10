package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.ItemType;

import java.util.Set;

public interface ItemTypeService {

    Set<ItemType> findAll();
    ItemType findById(Long id);
    void delete(ItemType itemType);
    ItemType save(ItemType itemType);

}
