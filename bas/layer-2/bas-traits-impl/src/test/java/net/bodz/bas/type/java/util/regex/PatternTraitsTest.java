package net.bodz.bas.type.java.util.regex;

import java.util.regex.Pattern;

import net.bodz.bas.lang.FinalNegotiation;
import net.bodz.bas.lang.INegotiation;
import net.bodz.bas.lang.NegotiationParameter;

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
        INegotiation n = new FinalNegotiation(//
                new NegotiationParameter(textformMode, globTextformMode));

        Pattern expected = Pattern.compile("\\Q\\E.*\\Q.xml\\E");
        Pattern actual = parse("*.xml", n);
        assertPatternEquals(expected, actual);
    }

}
