package com.org.ds.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Find com.org.ds.common elements between two sorted integer com.org.ds.arrays.
 * 
 * Signature of expected method:
 * 
 * public static List<Integer> findCommonElements(int[] a, int[] b) {...}
 */
public class FindCommonElements {
    public static List<Integer> findCommonElements(int[] a, int[] b) {
        if (a == null || b == null) {
            return null;
        }

        List<Integer> common = new ArrayList<Integer>();
        for (int aIndex = 0, bIndex = 0; aIndex < a.length && bIndex < b.length;) {
            if (a[aIndex] < b[bIndex]) {
                aIndex++;
            } else if (b[bIndex] < a[aIndex]) {
                bIndex++;
            } else {
                common.add(a[aIndex]);
                aIndex++;
                bIndex++;
            }
        }

        return common;
    }
}
