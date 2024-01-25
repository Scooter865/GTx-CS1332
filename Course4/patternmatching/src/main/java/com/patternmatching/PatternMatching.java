package com.patternmatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        List<Integer> matchIndices = new ArrayList<>();
        int m = pattern.length();
        int maxIter = text.length() - m;
        // Quickly return if pattern is longer than text. There won't be a match
        if (maxIter < 0) {
            return matchIndices;
        }
        int i = 0;
        Map<Character, Integer> lastOccurrenceTable = buildLastTable(pattern);

        // Check everything from 0 to the last possible index the pattern could start at
        while (i <= maxIter) {
            int j = m - 1;
            while ((j >= 0) && (comparator.compare(text.charAt(i+j), pattern.charAt(j)) == 0)) { 
                j--;
            }

            // Found a match
            if (j == -1) {
                matchIndices.add(i+j+1);
                i++;
            }
            // No match, need to shift
            else {
                int shiftIdx = lastOccurrenceTable.getOrDefault(text.charAt(i+j), -1);

                // Big shift when possible
                if (shiftIdx < j) {
                    i += j - shiftIdx;
                }
                // Otherwise shift by 1
                else {
                    i++;
                }
            }
        }
        return matchIndices;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        Map<Character, Integer> lastTable = new HashMap<>();
        int m = pattern.length();

        for (int i = 0; i < m; i++) {
            lastTable.put(pattern.charAt(i), i);
        }

        return lastTable;
    }

    /** 
     * KMP implementation
     * This calls the build failure table method then uses the failure table to run the KMP algorithm
     * 
      You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        Integer[] failTable = buildFailureTable(pattern, comparator);
        List<Integer> matchList = new ArrayList<>();
        int j = 0, k = 0;
        int n = text.length();
        int m = pattern.length();
        
        while (k < n) {
            if (comparator.compare(pattern.charAt(j), text.charAt(k)) == 0) {
                if (j == m-1) {
                    // found a match 
                    matchList.add(k-m+1);
                    // I think smart shifting is the right thing to do here
                    // Dumb shifting would be resetting j = 0
                    j = failTable[j-1];
                }
                else {
                    j++;
                    k++;
                }
            }
            else {
                // No math infomation, shift by 1
                if (j == 0) {
                    k++;
                }
                // Some match so far, shift smart
                else {
                    j = failTable[j-1];
                }
            }
        }
        return matchList;
    }

    /**
     * Builds the failure table for the KMP code
     * 
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return An integer array representing the failure map
     */
    public static Integer[] buildFailureTable(CharSequence pattern, CharacterComparator comparator) {
        int m = pattern.length();
        Integer failureTable[] = new Integer[m];
        failureTable[0] = 0;
        int i = 0, j = 1;

        while (j < m) {
            // Matching characters case
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                failureTable[j] = i+1;
                i++;
                j++;
            }
            else {
                // No match and no running prefix
                if (i == 0) {
                    failureTable[j] = 0;
                    j++;
                }
                // No match so back i up
                else {
                    i = failureTable[i-1];
                }
            }
        }
        return failureTable;
    }
}