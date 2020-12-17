package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.model.ItemAdd;
import uniloftsky.springframework.asamp.services.repositories.ItemAddRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemAddServiceImpl implements ItemAddService {

    private final ItemAddRepository itemAddRepository;
    private final ItemTypeService itemTypeService;
    private final ItemService itemService;

    public ItemAddServiceImpl(ItemAddRepository itemAddRepository, ItemTypeService itemTypeService, ItemService itemService) {
        this.itemAddRepository = itemAddRepository;
        this.itemTypeService = itemTypeService;
        this.itemService = itemService;
    }

    @Override
    public Set<ItemAdd> findAll() {
        Set<ItemAdd> adds = new HashSet<>();
        itemAddRepository.findAll().iterator().forEachRemaining(adds::add);
        return adds;
    }

    @Override
    public ItemAdd findById(Long id) {
        Optional<ItemAdd> itemAddOptional = itemAddRepository.findById(id);
        if (itemAddOptional.isEmpty()) {
            throw new RuntimeException("Expected item add not found!");
        }
        return itemAddOptional.get();
    }

    @Override
    public void delete(ItemAdd itemAdd) {
        itemAddRepository.delete(itemAdd);
    }

    @Override
    public ItemAdd save(ItemAdd itemAdd, Long itemTypeId) {
        Item foundItem = itemService.findByItemType_TypeName(itemTypeService.findById(itemTypeId).getTypeName());
        foundItem.setCount(foundItem.getCount() + itemAdd.getCount());
        itemService.save(foundItem);
        return itemAddRepository.save(itemAdd);
    }

    @Override
    public ItemAdd save(ItemAdd itemAdd) {
        return itemAddRepository.save(itemAdd);
    }
}
