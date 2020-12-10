package uniloftsky.springframework.asamp.services;

import uniloftsky.springframework.asamp.model.ItemType;
import uniloftsky.springframework.asamp.services.repositories.ItemTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    public ItemTypeServiceImpl(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public Set<ItemType> findAll() {
        Set<ItemType> types = new HashSet<>();
        itemTypeRepository.findAll().iterator().forEachRemaining(types::add);
        return types;
    }

    @Override
    public ItemType findById(Long id) {
        Optional<ItemType> itemTypeOptional = itemTypeRepository.findById(id);
        if(itemTypeOptional.isEmpty()) {
            throw new RuntimeException("Expected item type not found!");
        }
        return itemTypeOptional.get();
    }

    @Override
    public void delete(ItemType itemType) {
        itemTypeRepository.delete(itemType);
    }

    @Override
    public ItemType save(ItemType itemType) {
        return itemTypeRepository.save(itemType);
    }
}
