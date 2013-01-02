package net.bodz.bas.c.loader;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.Stdio;

public class ClassLoaderNodeTest
        extends Assert {

    @Test
    public void test1()
            throws IOException {
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();

        ClassLoaderNode[] nodes = ClassLoaderAnalyzer.mergeParents(scl, ccl);
        nodes[0].addTag("system class loader");
        nodes[1].addTag("context class loader");

        ClassLoaderNode.format(Stdio.cout, nodes);
    }

}
