package com.patternmatching;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class kmpTests
{
    CharSequence pattern;
    CharSequence text;
    List<Integer> expected = new ArrayList<>();
    CharacterComparator testCharacterComparator = new CharacterComparator();

    @BeforeEach
    public void setup() {
        expected.clear();
    }

    @Test
    public void OneOccurrence() {
        pattern = "BgX";
        text = "qRjFvHwYxPcDmZoNuTaBgXeLsKpVrIiJyAqGh";

        expected.add(19);

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
    }

    @Test
    public void MultipleSeperateOccurrence() {
        pattern = "BgX";
        text = "qRjFvBgXxPcDmZoNuTaBgXeLsKpBgXiJyAqGh";

        expected.add(5);
        expected.add(19);
        expected.add(27);

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
    }

    @Test
    public void OverlappingOccurrence() {
        pattern = "aabaa";
        text = "aaaaaabaabaaaaaaa";

        expected.add(4);
        expected.add(7);

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
    }

    @Test
    public void NoOccurrence() {
        pattern = "BgX";
        text = "qRjFvHwYxPcDmZoNuTaBgeLsKpVrIiJyAqGh";

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
        assertTrue(PatternMatching.kmp(pattern, text, testCharacterComparator).isEmpty());
    }

    @Test
    public void OccurrenceAtFront() {
        pattern = "BgX";
        text = "BgXqRjFvHwYxPcDmZoNuTaBgeLsKpVrIiJyAqGh";

        expected.add(0);

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
    }

    @Test
    public void OccurrenceAtBack() {
        pattern = "BgX";
        text = "qRjFvHwYxPcDmZoNuTaBgeLsKpVrIiJyAqGhBgX";

        expected.add(36);

        assertEquals(expected, PatternMatching.kmp(pattern, text, testCharacterComparator));
    }
}
