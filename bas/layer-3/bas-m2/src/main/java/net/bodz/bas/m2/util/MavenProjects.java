package net.bodz.bas.m2.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;

import net.bodz.bas.c.java.io.FileURL;

public class MavenProjects {

    public static ClassLoader createClassLoader(List<String> classpathElements) {
        int n = classpathElements.size();

        URL[] urls = new URL[n];
        for (int i = 0; i < n; i++) {
            File file = new File(classpathElements.get(i));
            urls[i] = FileURL.toURL(file);
        }

        ClassLoader pluginClassLoader = Thread.currentThread().getContextClassLoader();
        // if (pluginClassLoader == null)
        // pluginClassLoader = ClassLoader.getSystemClassLoader();
        URLClassLoader loader = new URLClassLoader(urls, pluginClassLoader);
        return loader;
    }

    public static ClassLoader createCompileClassLoader(MavenProject project)
            throws DependencyResolutionRequiredException {
        List<String> elements = project.getCompileClasspathElements();
        return createClassLoader(elements);
    }

    public static ClassLoader createRuntimeClassLoader(MavenProject project)
            throws DependencyResolutionRequiredException {
        List<String> elements = project.getRuntimeClasspathElements();
        return createClassLoader(elements);
    }

    public static ClassLoader createTestClassLoader(MavenProject project)
            throws DependencyResolutionRequiredException {
        List<String> elements = project.getTestClasspathElements();
        return createClassLoader(elements);
    }

}
