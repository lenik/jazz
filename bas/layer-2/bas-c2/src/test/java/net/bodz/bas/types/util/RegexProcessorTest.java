package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import net.bodz.bas.text.util.StringArray;
import net.bodz.bas.text.util.StringGrep;

import org.junit.Test;

public class RegexProcessorTest {

    static class TestFindAll {
        final Pattern pattern;
        final int group;

        public TestFindAll(Pattern pattern, int group) {
            this.pattern = pattern;
            this.group = group;
        }

        public void o(String input, String expected) {
            String[] words = StringGrep.findAll(input, pattern, group);
            String actual = StringArray.join("|", words);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testSpaceOverride() {
        // new SpaceOverride("\\s|//.*?\n");
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern pwords = regexProc.compile(//
                "(\\w+)(\\s*)", Pattern.DOTALL);
        System.out.println("pwords=" + pwords);
        TestFindAll d = new TestFindAll(pwords, 1); //
        d.o("hello", "hello");
        d.o("hello world", "hello|world");
        d.o("   a, b: @$* c   ", "a|b|c");
        d.o("a // IGNORED // \n b", "a|b");
        d.o("i/* lenik */ am 13/* 0-based*/year //-old! \nha ha!", "i|am|13|year|ha|ha");
    }

    @Test
    public void testSpaceOverride2() {
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern square = regexProc.compile(".*?\\[(.*?)\\]", Pattern.DOTALL);
        System.out.println("square=" + square);
        TestFindAll d = new TestFindAll(square, 1); //
        d.o("[hello]", "hello");
        d.o("[a]/* [b]*/ [c]", "a|c");
        d.o("none", "");
        // There's problem of last-match.
        // d.o("[a /*]]]*/] [[[d] //[[e] f]]", "a /*]]]*/|[[d"); //
        // d.o("[][/**/][//]", "|/**/"); //
    }

}
