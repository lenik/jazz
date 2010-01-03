package net.bodz.bas.lang.util;

import net.bodz.bas.lang.io.CharOuts;
import net.bodz.bas.lang.loader.Classpath;

import org.junit.Test;

public class ClasspathTest {

    @Test
    public void test1() {
        Classpath.dumpURLs(CharOuts.stdout);
    }

}
