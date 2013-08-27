package net.bodz.bas.type.java.util.regex;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.regex.PatternTypers;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;

public class PatternTypersTest
        extends PatternTypers {

    static void assertPatternEquals(Pattern a, Pattern b) {
        Assert.assertEquals(a.pattern(), b.pattern());
    }

    @Test
    public void testParseGlob()
            throws Exception {
        IOptions options = new Options().addOption(textformMode, globTextformMode);

        Pattern expected = Pattern.compile("\\Q\\E.*\\Q.xml\\E");
        Pattern actual = parse("*.xml", options);
        assertPatternEquals(expected, actual);
    }

}
