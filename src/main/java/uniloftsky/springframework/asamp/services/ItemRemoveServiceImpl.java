package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.model.ItemRemove;
import uniloftsky.springframework.asamp.services.repositories.ItemRemoveRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemRemoveServiceImpl implements ItemRemoveService {

    private final ItemRemoveRepository itemRemoveRepository;
    private final ItemService itemService;
    private final ItemTypeService itemTypeService;

    public ItemRemoveServiceImpl(ItemRemoveRepository itemRemoveRepository, ItemService itemService, ItemTypeService itemTypeService) {
        this.itemRemoveRepository = itemRemoveRepository;
        this.itemService = itemService;
        this.itemTypeService = itemTypeService;
    }

    @Override
    public Set<ItemRemove> findAll() {
        Set<ItemRemove> removes = new HashSet<>();
        itemRemoveRepository.findAll().iterator().forEachRemaining(removes::add);
        return removes;
    }

    @Override
    public ItemRemove findById(Long id) {
        Optional<ItemRemove> itemRemoveOptional = itemRemoveRepository.findById(id);
        if (itemRemoveOptional.isEmpty()) {
            throw new RuntimeException("Expected item remove not found!");
        }
        return itemRemoveOptional.get();
    }

    @Override
    public void delete(ItemRemove itemRemove) {
        itemRemoveRepository.delete(itemRemove);
    }

    @Override
    public ItemRemove save(ItemRemove itemRemove, Long itemTypeId) {
        Item foundItem = itemService.findByItemType_TypeName(itemTypeService.findById(itemTypeId).getTypeName());
        foundItem.setCount(foundItem.getCount() - itemRemove.getCount());
        itemService.save(foundItem);
        return itemRemoveRepository.save(itemRemove);
    }

    @Override
    public ItemRemove save(ItemRemove itemRemove) {
        return itemRemoveRepository.save(itemRemove);
    }
}
