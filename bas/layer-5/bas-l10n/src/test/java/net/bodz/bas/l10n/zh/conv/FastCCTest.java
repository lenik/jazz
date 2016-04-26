package net.bodz.bas.l10n.zh.conv;

import org.junit.Test;

public class FastCCTest
        extends AbstractChineseConverterTest {

    @Test
    public void testToSimplified() {
        testToSimplified(new FastCC());
    }

    @Test
    public void testToTraditional() {
        testToTraditional(new FastCC());
    }

}
