package net.bodz.bas.lang.util;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.loader.Classpath;

import org.junit.Test;

public class ClasspathTest {

    @Test
    public void test1() {
        Classpath.dumpURLs(CharOuts.stdout);
    }

}
