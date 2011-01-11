package net.bodz.bas.loader;

import junit.framework.TestCase;
import net.bodz.bas.sio.Stdio;

import org.junit.Test;

public class ClasspathTest
        extends TestCase {

    @Test
    public void test1() {
        Classpath.dumpURLs(Stdio.cout);
    }

}
