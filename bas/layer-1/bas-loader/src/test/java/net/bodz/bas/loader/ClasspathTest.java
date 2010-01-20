package net.bodz.bas.loader;

import net.bodz.bas.sio.Stdio;

import org.junit.Test;

public class ClasspathTest {

    @Test
    public void test1() {
        Classpath.dumpURLs(Stdio.cout);
    }

}
