package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.ItemAdd;

import java.util.Comparator;

public class ItemAddsAscComparatorById implements Comparator<ItemAdd> {

    @Override
    public int compare(ItemAdd o1, ItemAdd o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
