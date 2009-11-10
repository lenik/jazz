package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import net.bodz.bas.types.util.RegexProcessor.SpaceOverride;

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
            String[] words = Strings.findAll(input, pattern, group);
            String actual = Strings.join("|", words); //$NON-NLS-1$
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testSpaceOverride() {
        // new SpaceOverride("\\s|//.*?\n");
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern pwords = regexProc.compile(//
                "(\\w+)(\\s*)", Pattern.DOTALL); //$NON-NLS-1$
        System.out.println("pwords=" + pwords); //$NON-NLS-1$
        TestFindAll d = new TestFindAll(pwords, 1); //
        d.o("hello", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello world", "hello|world"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("   a, b: @$* c   ", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a // IGNORED // \n b", "a|b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("i/* lenik */ am 13/* 0-based*/year //-old! \nha ha!", //$NON-NLS-1$
                "i|am|13|year|ha|ha"); //$NON-NLS-1$
    }

    @Test
    public void testSpaceOverride2() {
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern square = regexProc.compile(".*?\\[(.*?)\\]", //$NON-NLS-1$
                Pattern.DOTALL);
        System.out.println("square=" + square); //$NON-NLS-1$
        TestFindAll d = new TestFindAll(square, 1); //
        d.o("[hello]", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[a]/* [b]*/ [c]", "a|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("none", ""); //$NON-NLS-1$ //$NON-NLS-2$
        // There's problem of last-match.
        // d.o("[a /*]]]*/] [[[d] //[[e] f]]", "a /*]]]*/|[[d"); //
        // d.o("[][/**/][//]", "|/**/"); //
    }

}
