package net.bodz.bas.l10n.zh;

import org.junit.Assert;
import org.junit.Test;

public class CharWidthConverterTest
        extends Assert {

    @Test
    public void testToFullWidth_Normal()
            throws Exception {
        class D {
            void o(String in, String expected) {
                String actual = CharWidthConverter.NORMAL.toFullWidth(in);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("hello", "ｈｅｌｌｏ");
        d.o("你好 空 格", "你好　空　格");
        d.o("你好, (#$%) ", "你好，（＃＄％）");
    }

    @Test
    public void testToHalfWidth_Normal()
            throws Exception {
        class D {
            void o(String in, String expected) {
                String actual = CharWidthConverter.NORMAL.toHalfWidth(in);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("ｈｅｌｌｏ", "hello");
        d.o("你好　空　格", "你好  空  格");
        d.o("你好（＃＄％）", "你好 (#$%) ");
    }

    @Test
    public void testToFullWidth_Compact()
            throws Exception {
        class D {
            void o(String in, String expected) {
                String actual = CharWidthConverter.COMPACT.toFullWidth(in);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("hello", "ｈｅｌｌｏ");
        d.o("你好 空 格", "你好　空　格");
        d.o("你好, (#$%) ", "你好，（＃＄％）");
    }

    @Test
    public void testToHalfWidth_Compact()
            throws Exception {
        class D {
            void o(String in, String expected) {
                String actual = CharWidthConverter.COMPACT.toHalfWidth(in);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("ｈｅｌｌｏ", "hello");
        d.o("你好　空　格", "你好 空 格");
        d.o("你好（＃＄％）", "你好(#$%)");
    }

    public static void main(String[] args) {
        StringBuilder half = new StringBuilder();
        StringBuilder full = new StringBuilder();
        for (char ch = 0x21; ch <= 0x7E; ch++) {
            StringBuilder sb = new StringBuilder();
            sb.append(ch);
            String fullChar = CharWidthConverter.NORMAL.toFullWidth(sb.toString());
            half.append(ch);
            full.append(fullChar);
        }
        System.out.println(half);
        System.out.println(full);
    }

}
