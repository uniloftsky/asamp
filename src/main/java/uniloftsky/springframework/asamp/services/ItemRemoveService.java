package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.ItemRemove;

import java.util.Set;

public interface ItemRemoveService {

    Set<ItemRemove> findAll();
    ItemRemove findById(Long id);
    void delete(ItemRemove itemRemove);
    ItemRemove save(ItemRemove itemRemove);

}
