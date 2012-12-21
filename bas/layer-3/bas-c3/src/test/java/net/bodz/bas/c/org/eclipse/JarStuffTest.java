package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.string.StringFeature;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;

public class JarStuffTest
        extends Assert {

    static Set<String> commonOutputDirs;
    {
        commonOutputDirs = new HashSet<String>();
        commonOutputDirs.add("test-classes");
        commonOutputDirs.add("test.classes");
        commonOutputDirs.add("classes");
    }

    private String magic = "MaGiC-GoOd..";

    private File baseDir;
    JavaProject project;

    public JarStuffTest() {
        baseDir = JavaProjectBaseDir.forClass(JarStuffTest.class);
        project = JavaProject.forClass(JarStuffTest.class);
    }

    @Test
    public void testFindProjectBase()
            throws Exception {
        System.out.println("[projbase] " + baseDir);
    }

    @Test
    public void testGetRootFile()
            throws Exception {
        File classpath;

        classpath = ClassResource.getRootFile(JarStuffTest.class);
        System.out.println("[outbase] (test class) => " + classpath);
        String name = classpath.getName();
        assertTrue(commonOutputDirs.contains(name));

        classpath = ClassResource.getRootFile(Object.class);
        System.out.println("[outbase] Object.class => " + classpath);
        assertEquals(".jar", FilePath.getExtension(classpath, true));
    }

    @Test
    public void testGetSrcURL_findMagic()
            throws IOException {
        URL src = project.findSourceURL(JarStuffTest.class);
        if (src != null) {
            String code = new URLResource(src)//
                    .tooling()._for(StreamReading.class).readString();
            assertEquals(1, StringFeature.count(code, magic));
        }
    }

    @Test
    public void testGetSrcURL_rtjar()
            throws IOException {
        String classRes = ClassResource.getClassBytesURL(Object.class).toString();
        URL src = project.findSourceURL(Object.class);
        if (src != null) {
            String srcRes = src.toString();
            assertNotNull("can't find src of Object.class", srcRes);
            String srcExpected = classRes.replace(".class", ".java");
            srcExpected = srcExpected.replace("rt.jar!", "rt-src.jar!");
            assertEquals(srcExpected, srcRes);
        }
    }

    @Test
    public void testGetSrcURLWithName()
            throws IOException {
        // URL url = BuildPath.getSrcURLWithName(BasicCLI.class, "a/Option.java");
        // System.out.println("[src/name] (BasicCLI, a/Option) => " + url);
    }

}
