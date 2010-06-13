package net.bodz.bas.type.parser;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class GlobPatternParserTest {

    static void assertEquals(Pattern a, Pattern b) {
        Assert.assertEquals(a.pattern(), b.pattern());
    }

    @Test
    public void testCompileGlob()
            throws Exception {
        assertEquals(Pattern.compile("\\Q\\E.*\\Q.xml\\E"), //
                GlobPatternParser._parse("*.xml", 0));
    }

}
