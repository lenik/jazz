package net.bodz.bas.i18n.dstr;

import org.junit.Assert;
import org.junit.Test;

public class DomainStringTest
        extends Assert {

    String pl1 = "Default<p  \n lang=\"zh-cn\"  />中文<p lang='et'>Esperanto";
    DomainString dstr1 = DomainString.parseParaLang(pl1);

    @Test
    public void testDumpContent() {
        // System.out.println(dstr.dumpContent());
        assertEquals("null: Default\net: Esperanto\nzh-cn: 中文\n", dstr1.dumpContent());
    }

    @Test
    public void toParaLangString() {
        assertEquals("Default\n<p lang=\"et\">Esperanto\n<p lang=\"zh-cn\">中文", dstr1.toParaLangString());
    }

    @Test
    public void toMultiLangString() {
        assertEquals("\"Default\"\net \"Esperanto\"\nzh-cn \"中文\"", dstr1.toMultiLangString());
    }

    @Test
    public void testParseNullString() {
        DomainString nul = new DomainString(null);
        assertNull(nul.toString());
        assertEquals("", nul.toMultiLangString());
        assertEquals("", nul.toParaLangString());
    }

    @Test
    public void testParseNullMultiLang() {
        DomainString dstr = DomainString.parseMultiLang("");
        assertNull(dstr.value);
    }

    @Test
    public void testParseNullParaLang() {
        DomainString dstr = DomainString.parseParaLang("");
        assertEquals("", dstr.value);
    }

}
