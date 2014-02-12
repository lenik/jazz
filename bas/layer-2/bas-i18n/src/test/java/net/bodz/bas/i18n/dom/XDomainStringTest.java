package net.bodz.bas.i18n.dom;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class XDomainStringTest
        extends Assert {

    String pl1 = "Default<p  \n lang=\"zh-cn\"  />中文<p lang='et'>Esperanto";
    iString dstr1 = iString.fn.parseParaLangString(pl1);

    String _hello = "Hello<p  \n lang=\"zh-cn\"  />你好<p lang='et'>Saluton";
    String _space = " <p  \n lang=\"zh\"  />　";
    String _world = "World<p  \n lang=\"zh-cn\"  />世界<p lang='et'>La mondo";
    iString hello = iString.fn.parseParaLangString(_hello);
    iString space = iString.fn.parseParaLangString(_space);
    iString world = iString.fn.parseParaLangString(_world);

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
        iString nul = new XiString();
        assertNull(nul.toString());
        assertEquals("", nul.toMultiLangString());
        assertEquals("", nul.toParaLangString());
    }

    @Test
    public void testParseNullMultiLang()
            throws ParseException {
        XiString dstr = new MultiLangStringParser().parse("");
        assertNull(dstr.getValue());
    }

    @Test
    public void testParseNullParaLang() {
        XiString dstr = (XiString) iString.fn.parseParaLangString("");
        assertEquals("", dstr.getValue());
    }

    @Test
    public void testSize() {
        assertEquals(3, dstr1.size());
    }

    @Test
    public void testRemoveNotExisted() {
        String bad = dstr1.remove("bad");
        assertNull(bad);
    }

    @Test
    public void testRemoveExisted() {
        String et = dstr1.remove("et");
        assertEquals("Esperanto", et);
        assertEquals(2, dstr1.size());
    }

    @Test
    public void testEquals() {
        assertEquals(dstr1, dstr1);

        iString copy = dstr1.clone();
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
        iString c = new XiString().concat(hello);
        assertEquals(hello, c);
    }

    iString s1 = new XiString("x1");
    iString s2 = new XiString("x2");
    {
        s1.put("zh-cn", "zc1");
        s1.put("et", "e1");
        s2.put("zh", "z2");
    }

    @Test
    public void testConcats() {
        iString s12 = new XiString("x1x2");
        s12.put("zh-cn", "zc1");
        s12.put("et", "e1");
        s12.put("zh", "z2");
        iString cat12 = s1.clone().concat(s2);
        assertEquals(s12, cat12);

        iString s21 = new XiString("x2x1");
        s21.put("zh-cn", "zc1");
        s21.put("et", "e1");
        s21.put("zh", "z2");
        iString cat21 = s2.clone().concat(s1);
        assertEquals(s21, cat21);
    }

    @Test
    public void testJoins() {
        iString j12 = new XiString("x1x2");
        j12.put("zh-cn", "zc1z2");
        j12.put("et", "e1x2");
        j12.put("zh", "x1z2");
        iString jon12 = s1.clone().join(s2);
        // System.out.println("expect=" + j12.toMultiLangString());
        // System.out.println("actual=" + jon12.toMultiLangString());
        assertEquals(j12, jon12);
    }

}
