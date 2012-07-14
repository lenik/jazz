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
        DomainString nul = new DomainString();
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
    public void testEquals() {
        assertEquals(dstr1, dstr1);

        DomainString copy = dstr1.clone();
        assertNotSame(dstr1, copy);
        assertEquals(dstr1, copy);

        copy.pull("zh-cn");
        String _dstr1 = dstr1.toMultiLangString();
        String _copy1 = copy.toMultiLangString();
        assertFalse(_dstr1.equals(_copy1));
        assertEquals(dstr1, copy);
    }

    @Test
    public void testConcat0() {
        DomainString c = new DomainString().concat(hello);
        assertEquals(hello, c);
    }

    DomainString s1 = new DomainString("x1");
    DomainString s2 = new DomainString("x2");
    {
        s1.put("zh-cn", "zc1");
        s1.put("et", "e1");
        s2.put("zh", "z2");
    }

    @Test
    public void testConcats() {
        DomainString s12 = new DomainString("x1x2");
        s12.put("zh-cn", "zc1");
        s12.put("et", "e1");
        s12.put("zh", "z2");
        DomainString cat12 = s1.clone().concat(s2);
        assertEquals(s12, cat12);

        DomainString s21 = new DomainString("x2x1");
        s21.put("zh-cn", "zc1");
        s21.put("et", "e1");
        s21.put("zh", "z2");
        DomainString cat21 = s2.clone().concat(s1);
        assertEquals(s21, cat21);
    }

    @Test
    public void testJoins() {
        DomainString j12 = new DomainString("x1x2");
        j12.put("zh-cn", "zc1z2");
        j12.put("et", "e1x2");
        j12.put("zh", "x1z2");
        DomainString jon12 = s1.clone().join(s2);
        System.out.println("expect=" + j12.toMultiLangString());
        System.out.println("actual=" + jon12.toMultiLangString());
        assertEquals(j12, jon12);
    }

}
