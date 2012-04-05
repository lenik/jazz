package net.bodz.mda.xjdoc.base;


import net.bodz.mda.xjdoc.dstr.DomainString;
import net.bodz.mda.xjdoc.dstr.MultiLangStringParser;

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
