package org.example.algorithms;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();
    private long operations = 0;

    public void makeSet(Iterable<String> vertices) {
        for (String v : vertices) {
            parent.put(v, v);
            rank.put(v, 0);
        }
    }
    public String find(String v) {
        operations++;
        if (!parent.get(v).equals(v)) {
            parent.put(v, find(parent.get(v)));
        }
        return parent.get(v);
    }
    public void union(String a, String b) {
        operations++;
        String rootA = find(a);
        String rootB = find(b);
        if (rootA.equals(rootB)) return;

        if (rank.get(rootA) < rank.get(rootB)) {
            parent.put(rootA, rootB);
        } else if (rank.get(rootA) > rank.get(rootB)) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rank.get(rootA) + 1);
        }
    }
    public long getOperations() {
        return operations;
    }
}
