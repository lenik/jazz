package net.bodz.bas.type.java.util.regex;

import static net.bodz.bas.rtx.Negotiation.list;
import static net.bodz.bas.rtx.Negotiation.option;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.regex.PatternMdaFeatures;
import net.bodz.bas.rtx.INegotiation;

public class PatternMdaFeaturesTest
        extends PatternMdaFeatures {

    static void assertPatternEquals(Pattern a, Pattern b) {
        Assert.assertEquals(a.pattern(), b.pattern());
    }

    @Test
    public void testParseGlob()
            throws Exception {
        INegotiation n = list(option(textformMode, globTextformMode));

        Pattern expected = Pattern.compile("\\Q\\E.*\\Q.xml\\E");
        Pattern actual = parse("*.xml", n);
        assertPatternEquals(expected, actual);
    }

}
