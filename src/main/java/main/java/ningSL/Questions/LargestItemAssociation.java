package main.java.ningSL.Questions;

import java.util.*;
import java.util.stream.Collectors;

public class LargestItemAssociation {
    public static List<String> largestItemAssociation(List<PairString> pairs) {
        if (pairs.isEmpty())
            return null;
        PriorityQueue<Set<String>> max_heap = new PriorityQueue<>(//
                (l1, l2) -> Integer.compare(l2.size(), l1.size()));//
        Collections.sort(pairs, (a, b) -> a.first.compareTo(b.first));
        for (int i = 0; i < pairs.size(); i++) {
            Set<String> list = new TreeSet<>(Arrays.asList(pairs.get(i).first, pairs.get(i).second));
            for (int j = i + 1; j < pairs.size(); j++) {
                merge(list, pairs.get(j));
            }
            max_heap.add(list);
        }
        return max_heap.poll().stream().collect(Collectors.toList());
    }

    private static void merge(Set<String> list, PairString pairString) {
        if (list.contains(pairString.first) && list.contains(pairString.second))
            return;
        if (list.contains(pairString.first)) {
            list.add(pairString.second);
        } else if (list.contains(pairString.second)) {
            list.add(pairString.first);
        }
    }


    public static void main(String[] args) {
        List<PairString> pairs = Arrays.asList( //
                new PairString("item3", "item2"), // -> item1, item3, item2
                new PairString("item3", "item4"), //
                new PairString("item2", "item1") //
        );

        System.out.println(largestItemAssociation(pairs));
    }
}

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}