package net.bodz.bas.l10n.zh.conv;

import org.junit.Assert;

public abstract class AbstractChineseConverterTest
        extends Assert {

    protected void testToSimplified(final IChineseConverter cc) {
        class D {
            void o(String in, String expected) {
                String out = cc.toSimplified(in);
                assertEquals(expected, out);
            }
        }
        D d = new D();
        d.o("你好", "你好");
        d.o("計算機", "计算机");
    }

    protected void testToTraditional(final IChineseConverter cc) {
        class D {
            void o(String in, String expected) {
                String out = cc.toTraditional(in);
                assertEquals(expected, out);
            }
        }
        D d = new D();
        d.o("你好", "你好");
        d.o("计算机", "計算機");
    }

}
