package net.bodz.bas.type.java.util.regex;

import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.PatternTraits;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.IndexedNegotiation;
import net.bodz.bas.lang.negotiation.Option;

import org.junit.Assert;
import org.junit.Test;

public class PatternTraitsTest
        extends PatternTraits {

    static void assertPatternEquals(Pattern a, Pattern b) {
        Assert.assertEquals(a.pattern(), b.pattern());
    }

    @Test
    public void testParseGlob()
            throws Exception {
        INegotiation n = new IndexedNegotiation(//
                new Option(textformMode, globTextformMode));

        Pattern expected = Pattern.compile("\\Q\\E.*\\Q.xml\\E");
        Pattern actual = parse("*.xml", n);
        assertPatternEquals(expected, actual);
    }

}
