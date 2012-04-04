package lenik.lab.xjdoc.base;

import lenik.lab.xjdoc.dstr.DomainString;
import lenik.lab.xjdoc.dstr.MultiLangStringParser;

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
