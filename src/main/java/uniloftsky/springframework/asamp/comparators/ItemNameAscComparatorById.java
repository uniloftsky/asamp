package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.ItemName;

import java.util.Comparator;

public class ItemNameAscComparatorById implements Comparator<ItemName> {

    @Override
    public int compare(ItemName o1, ItemName o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
