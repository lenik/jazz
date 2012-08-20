package net.bodz.bas.loader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.Stdio;

public class ClasspathTest
        extends Assert {

    @Test
    public void test1() {
        Classpath.dumpURLs(Stdio.cout);
    }

}
