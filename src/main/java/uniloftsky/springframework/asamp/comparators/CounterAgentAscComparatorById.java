package uniloftsky.springframework.asamp.comparators;

import uniloftsky.springframework.asamp.model.CounterAgent;

import java.util.Comparator;

public class CounterAgentAscComparatorById implements Comparator<CounterAgent> {

    @Override
    public int compare(CounterAgent o1, CounterAgent o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
