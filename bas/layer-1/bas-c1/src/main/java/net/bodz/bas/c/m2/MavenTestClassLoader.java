package net.bodz.bas.c.m2;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.err.UnexpectedException;

public class MavenTestClassLoader {

    public static URLClassLoader createMavenTestClassLoader(URLClassLoader loader) {
        LinkedHashSet<Path> classpaths = new LinkedHashSet<>();
        LinkedHashSet<Path> testClasspaths = new LinkedHashSet<>();
        for (Path classpath : URLClassLoaders.getLocalURLs(loader)) {
            if (classpaths.add(classpath)) {
                // target/classes/...
                // target/test-classes/...
                String path = classpath.toString();
                String testPath = path.replaceFirst("/target/classes", "/target/test-classes");
                if (!testPath.equals(path)) {
                    Path testClasspath = Paths.get(testPath);
                    testClasspaths.add(testClasspath);
                }
            }
        }
        testClasspaths.removeAll(classpaths);

        URL[] testClasspathUrls = new URL[testClasspaths.size()];
        int index = 0;
        try {
            for (Path testClasspath : testClasspaths) {
                URL url = testClasspath.toUri().toURL();
                testClasspathUrls[index++] = url;
            }
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }

        URLClassLoader testClassLoader = new URLClassLoader(testClasspathUrls, loader);
        return testClassLoader;
    }

}
