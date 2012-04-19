package net.bodz.bas.loader.scan.m2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashSet;

import net.bodz.bas.err.UnexpectedException;

public class TestClassLoader {

    public static URLClassLoader createMavenTestClassLoader(URLClassLoader loader) {
        LinkedHashSet<File> classpaths = new LinkedHashSet<File>();
        LinkedHashSet<File> testClasspaths = new LinkedHashSet<File>();
        for (File classpath : UCLDumper.getLocalClasspaths(loader)) {
            if (classpaths.add(classpath)) {
                // target/classes/...
                // target/test-classes/...
                String path = classpath.getPath();
                String testPath = path.replaceFirst("/target/classes", "/target/test-classes");
                if (!testPath.equals(path)) {
                    File testClasspath = new File(testPath);
                    testClasspaths.add(testClasspath);
                }
            }
        }
        testClasspaths.removeAll(classpaths);

        URL[] testClasspathUrls = new URL[testClasspaths.size()];
        int index = 0;
        try {
            for (File testClasspath : testClasspaths) {
                URL url = testClasspath.toURI().toURL();
                testClasspathUrls[index++] = url;
            }
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }

        URLClassLoader testClassLoader = new URLClassLoader(testClasspathUrls, loader);
        return testClassLoader;
    }

}
