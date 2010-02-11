package net.bodz.bas.l10n.zh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConvertCharacterWidthTest {

    @Test
    public void testToFullWidth()
            throws Exception {
        class D {
            void o(String in, String expected) {
                String actual = ConvertCharacterWidth.toFullWidth(in);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("hello", "ｈｅｌｌｏ");
        d.o("你好, 空格", "你好，空格");
    }

}
