package uniloftsky.springframework.asamp.services;

import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.ItemAdd;
import uniloftsky.springframework.asamp.services.repositories.ItemAddRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemAddServiceImpl implements ItemAddService {

    private final ItemAddRepository itemAddRepository;

    public ItemAddServiceImpl(ItemAddRepository itemAddRepository) {
        this.itemAddRepository = itemAddRepository;
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
    public ItemAdd save(ItemAdd itemAdd) {
        return itemAddRepository.save(itemAdd);
    }
}
