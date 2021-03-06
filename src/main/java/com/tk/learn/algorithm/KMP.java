package com.tk.learn.algorithm;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String t = "ababvbaa9abababcabababcabcdabdabcababsdbababca";
        String s = "acabaca";
        int[] next = getNext(s);
        int i = kmp(t, s, next);

        System.out.println(Arrays.toString(next));
        System.out.println(i);
    }

    private static int kmp(String t, String s, int[] next) {
        char[] n = t.toCharArray();
        char[] p = s.toCharArray();
        int i = 0, j = 0;

        while (i < n.length && j < p.length) {
            if (j == -1 || n[i] == p[j]) {
                i++;
                j++;
            } else
                j = next[j];
        }

        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String s) {
        int[] next = new int[s.length()];
        char[] p = s.toCharArray();
        next[0] = -1;

        int i = 0, j = -1;
        while (i < p.length - 1) {
            if (j == -1 || p[i] == p[j]) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

}
