package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.regex.Pattern;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.RegexProcessor.SpaceOverride;

import org.junit.Test;

public class RegexProcessorTest {

    static class FindAll extends _TestEval<String> {
        final Pattern pattern;
        final int     group;

        public FindAll(Pattern pattern, int group) {
            this.pattern = pattern;
            this.group = group;
        }

        public Object eval(String input) throws Throwable {
            if (isBreakpoint())
                System.err.println(input);
            String[] words = Strings.findAll(input, pattern, group);
            return Strings.join("|", words); //$NON-NLS-1$
        }
    }

    @Test
    public void testSpaceOverride() {
        // new SpaceOverride("\\s|//.*?\n");
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern pwords = regexProc.compile(//
                "(\\w+)(\\s*)", Pattern.DOTALL); //$NON-NLS-1$
        System.out.println("pwords=" + pwords); //$NON-NLS-1$
        TestDefs.tests(new FindAll(pwords, 1), //
                EQ("hello", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello world", "hello|world"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("   a, b: @$* c   ", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a // IGNORED // \n b", "a|b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("i/* lenik */ am 13/* 0-based*/year //-old! \nha ha!", //$NON-NLS-1$
                        "i|am|13|year|ha|ha"), // //$NON-NLS-1$
                END);
    }

    @Test
    public void testSpaceOverride2() {
        final SpaceOverride regexProc = RegexProcessor.javaComments;
        final Pattern square = regexProc.compile(".*?\\[(.*?)\\]", //$NON-NLS-1$
                Pattern.DOTALL);
        System.out.println("square=" + square); //$NON-NLS-1$
        TestDefs.tests(new FindAll(square, 1), //
                EQ("[hello]", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[a]/* [b]*/ [c]", "a|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("none", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                // There's problem of last-match.
                // EQ("[a /*]]]*/] [[[d] //[[e] f]]", "a /*]]]*/|[[d"), //
                // EQ("[][/**/][//]", "|/**/"), //
                END);
    }
}
