package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.ItemName;
import uniloftsky.springframework.asamp.services.repositories.ItemNameRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemNameServiceImpl implements ItemNameService {

    private final ItemNameRepository itemNameRepository;

    public ItemNameServiceImpl(ItemNameRepository itemNameRepository) {
        this.itemNameRepository = itemNameRepository;
    }

    @Override
    public Set<ItemName> findAll() {
        Set<ItemName> types = new HashSet<>();
        itemNameRepository.findAll().iterator().forEachRemaining(types::add);
        return types;
    }

    @Override
    public ItemName findById(Long id) {
        Optional<ItemName> itemNameOptional = itemNameRepository.findById(id);
        if(itemNameOptional.isEmpty()) {
            throw new RuntimeException("Expected item name not found!");
        }
        return itemNameOptional.get();
    }

    @Override
    public void delete(ItemName itemName) {
        itemNameRepository.delete(itemName);
    }

    @Override
    public ItemName save(ItemName itemName) {
        return itemNameRepository.save(itemName);
    }

}
