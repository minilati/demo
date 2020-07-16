package com.tk.demo.algorithm;

import java.util.*;

public class Greedy {

    public static void main(String[] args) {
        HashSet<String> allAreas = new HashSet<>(Arrays.asList("BJ", "SH", "TJ", "GZ", "SZ", "CD", "HZ", "DL"));
        HashSet<String> a = new HashSet<>(Arrays.asList("BJ", "SH", "TJ"));
        HashSet<String> b = new HashSet<>(Arrays.asList("GZ", "BJ", "SZ"));
        HashSet<String> c = new HashSet<>(Arrays.asList("CD", "SH", "HZ"));
        HashSet<String> d = new HashSet<>(Arrays.asList("SH", "TJ"));
        HashSet<String> e = new HashSet<>(Arrays.asList("HZ", "DL"));
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        broadcasts.put("A", a);
        broadcasts.put("B", b);
        broadcasts.put("C", c);
        broadcasts.put("D", d);
        broadcasts.put("E", e);

        ArrayList<String> selects = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();
        String maxKey = null;
        int maxNum = 0;

        while (!allAreas.isEmpty()) {
            for (String key : broadcasts.keySet()) {
                tempSet.addAll(broadcasts.get(key));
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxNum)) {
                    maxKey = key;
                    maxNum = tempSet.size();
                }
                tempSet.clear();
            }
            selects.add(maxKey);
            allAreas.removeAll(broadcasts.get(maxKey));
            maxKey = null;
        }
        System.out.println(selects);
    }
}
