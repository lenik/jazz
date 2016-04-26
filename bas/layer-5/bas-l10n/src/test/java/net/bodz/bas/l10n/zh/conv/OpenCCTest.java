package net.bodz.bas.l10n.zh.conv;

import org.junit.Test;

public class OpenCCTest
        extends AbstractChineseConverterTest {

    @Test
    public void testToSimplified() {
        testToSimplified(new OpenCC());
    }

    @Test
    public void testToTraditional() {
        testToTraditional(new OpenCC());
    }

}
