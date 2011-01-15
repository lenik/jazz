package net.bodz.bas.loader;

import net.bodz.bas.sio.Stdio;

import org.junit.Assert;
import org.junit.Test;

public class ClasspathTest
        extends Assert {

    @Test
    public void test1() {
        Classpath.dumpURLs(Stdio.cout);
    }

}
