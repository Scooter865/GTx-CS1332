package com.patternmatching;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class buildFailureTableTests 
{
    CharacterComparator testCharacterComparator = new CharacterComparator();

    @BeforeEach
    public void setup() {

    }

    @Test
    public void OneChar() {
        Integer[] ans = {0};
        assertArrayEquals(ans, PatternMatching.buildFailureTable("A", testCharacterComparator));
    }

    @Test
    public void RandomChars() {
        CharSequence pattern = "revararev";
        Integer[] ans = {0, 0, 0, 0, 1, 0, 1, 2, 3};

        assertArrayEquals(ans, PatternMatching.buildFailureTable(pattern, testCharacterComparator));
    }
}
