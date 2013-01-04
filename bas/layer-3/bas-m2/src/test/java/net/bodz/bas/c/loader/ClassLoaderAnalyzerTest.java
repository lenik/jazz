package net.bodz.bas.c.loader;

import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.Stdio;

public class ClassLoaderAnalyzerTest
        extends Assert {

    @Test
    public void analyzeSCLandCCL()
            throws IOException {
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();

        ClassLoaderNode[] nodes = ClassLoaderAnalyzer.mergeParents(scl, ccl);
        nodes[0].addTag("system class loader");
        nodes[1].addTag("context class loader");

        Set<ClassLoaderNode> roots = ClassLoaderAnalyzer.getRoots(nodes);

        ClassLoaderTreeFormatter formatter = new ClassLoaderTreeFormatter();
        for (ClassLoaderNode root : roots) {
            formatter.format(Stdio.cout, root);
        }
    }

    public static void main(String[] args)
            throws IOException {
        new ClassLoaderAnalyzerTest().analyzeSCLandCCL();
    }

}
