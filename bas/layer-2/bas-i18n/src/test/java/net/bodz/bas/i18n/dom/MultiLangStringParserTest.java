package net.bodz.bas.i18n.dom;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class MultiLangStringParserTest
        extends Assert {

    @Test
    public void testParseMultiLang()
            throws ParseException {
        MultiLangStringParser parser = new MultiLangStringParser();
        iString istr = parser.parse("\"default\" zh-cn \"中文\" sjy et \"Esperanto\"");
        assertEquals("null: default\net: Esperanto\nzh-cn: 中文\n", //
                istr.dumpContent());
    }

    @Test
    public void testParseEscapes()
            throws ParseException {
        MultiLangStringParser parser = new MultiLangStringParser();
        iString istr = parser.parse("\"one\\t\u0031\"");
        String root = istr.get("");
        assertEquals("one\t1", root);
    }

}
