package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.ItemType;

import java.util.Comparator;

public class ItemTypeAscComparatorById implements Comparator<ItemType> {

    @Override
    public int compare(ItemType o1, ItemType o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
