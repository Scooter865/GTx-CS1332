package com.iterativesorts;

import java.util.Comparator;

/* Comparator helper class */
public class SortComparator implements Comparator<Integer> {

    public int compare(Integer first, Integer second) {
        return (first - second);
    }
}
