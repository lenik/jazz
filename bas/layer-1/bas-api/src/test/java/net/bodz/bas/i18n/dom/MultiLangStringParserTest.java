package net.bodz.bas.i18n.dom;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.MultiLangStringParser;

import org.junit.Assert;
import org.junit.Test;

public class MultiLangStringParserTest
        extends Assert {

    @Test
    public void testParse() {
        MultiLangStringParser parser = new MultiLangStringParser();
        DomainString dstr = parser.parse("\"default\" zh-cn \"中文\" sjy et \"Esperanto\"");
        assertEquals("null: default\net: Esperanto\nzh-cn: 中文\n", //
                dstr.dumpContent());
    }

}
