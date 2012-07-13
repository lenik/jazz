package net.bodz.bas.i18n.dom;

import org.junit.Assert;
import org.junit.Test;

public class DomainStringTest
        extends Assert {

    String pl1 = "Default<p  \n lang=\"zh-cn\"  />中文<p lang='et'>Esperanto";
    DomainString dstr1 = DomainString.parseParaLang(pl1);

    String _hello = "Hello<p  \n lang=\"zh-cn\"  />你好<p lang='et'>Saluton";
    String _space = " <p  \n lang=\"zh\"  />　";
    String _world = "World<p  \n lang=\"zh-cn\"  />世界<p lang='et'>La mondo";
    DomainString hello = DomainString.parseParaLang(_hello);
    DomainString space = DomainString.parseParaLang(_space);
    DomainString world = DomainString.parseParaLang(_world);

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

    @Test
    public void testSize() {
        assertEquals(3, dstr1.size());
    }

    @Test
    public void testRemoveNotExisted() {
        DomainString bad = dstr1.remove("bad");
        assertNull(bad);
    }

    @Test
    public void testRemoveExisted() {
        DomainString et = dstr1.remove("et");
        assertNotNull(et);
        assertEquals("et", et.getDomain());
        assertEquals(2, dstr1.size());
    }

    @Test
    public void testConcat1() {
        DomainString c = new DomainString().concat(hello);
        assertEquals(hello, c);
    }

}
