package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.Item;

import java.util.Comparator;

public class ItemAscComparatorById implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
