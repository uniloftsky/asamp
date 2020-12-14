package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.ItemRemove;

import java.util.Comparator;

public class ItemRemovesAscComparatorById implements Comparator<ItemRemove> {

    @Override
    public int compare(ItemRemove o1, ItemRemove o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
