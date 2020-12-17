package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.services.repositories.ItemRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        Set<Item> items = new HashSet<>();
        itemRepository.findAll().iterator().forEachRemaining(items::add);
        return items;
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isEmpty()) {
            throw new RuntimeException("Expected item not found!");
        }
        return itemOptional.get();
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findByItemType_TypeName(String itemType) {
        return itemRepository.findByItemType_TypeName(itemType);
    }
}
