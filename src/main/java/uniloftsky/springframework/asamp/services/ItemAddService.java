package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.ItemAdd;

import java.util.Set;

public interface ItemAddService {

    Set<ItemAdd> findAll();
    ItemAdd findById(Long id);
    void delete(ItemAdd itemAdd);
    ItemAdd save(ItemAdd itemAdd);

}
