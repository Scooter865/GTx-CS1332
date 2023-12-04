package com.patternmatching;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class buildLastTableTests 
{
    Map<Character, Integer> expected = new HashMap<>();

    @BeforeEach
    public void setup() {
        expected.clear();
    }

    @Test
    public void OneChar() {
        expected.put('A', 0);

        assertEquals(expected, PatternMatching.buildLastTable("A"));
    }

    @Test
    public void RandomChars() {
        CharSequence text = "qRjFvHwYxPcDmZoNuTaBgXeLsKpVrIiJyAqGh";
        expected.put('q', 34);
        expected.put('R', 1);
        expected.put('h', 36);

        Map<Character, Integer> ans = PatternMatching.buildLastTable(text);

        assertEquals(expected.get('q'), ans.get('q'));
        assertEquals(expected.get('R'), ans.get('R'));
        assertEquals(expected.get('h'), ans.get('h'));
    }
}
