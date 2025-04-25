package net.bodz.bas.fn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeqMatcherTest
        extends Assert {

    SeqMatcher<String> matcher;
    List<String> testInputs;

    @Before
    public void setUp() {
        testInputs = new ArrayList<>();
        testInputs.add("A");
        testInputs.add("B");
        testInputs.add("C");
        testInputs.add("D");
    }

    @Test
    public void testEmptyPattern() {
        matcher = new SeqMatcher<>();
        assertTrue("Empty pattern should return true for any input", matcher.test("any"));
        assertTrue("Empty pattern should return true for any input", matcher.test("123"));
    }

    @Test
    public void testSingleElementPattern() {
        matcher = new SeqMatcher<>("A");
        assertTrue("Single element pattern should return true when input matches", matcher.test("A"));
        assertFalse("Single element pattern should return false when input is different", matcher.test("B"));
    }

    @Test
    public void testPatternMatch() {
        matcher = new SeqMatcher<>("A", "B");
        assertFalse("Pattern [A, B] should return false for first input 'A'", matcher.test("A"));
        assertTrue("Pattern [A, B] should return true for second input 'B'", matcher.test("B"));
        assertFalse("Pattern [A, B] should return false for third input 'C'", matcher.test("C"));
    }

    @Test
    public void testPatternMatchWithMultipleInputs() {
        matcher = new SeqMatcher<>("A", "B");
        // Test for the sequence [A, B, C]
        assertFalse("Pattern [A, B] should return false for first input 'A'", matcher.test("A"));
        assertTrue("Pattern [A, B] should return true for second input 'B'", matcher.test("B"));
        assertFalse("Pattern [A, B] should return false for third input 'C' (buffer now [B, C])", matcher.test("C"));
        assertFalse("Pattern [A, B] should return false for fourth input 'D' (buffer now [C, D])", matcher.test("D"));
    }

}