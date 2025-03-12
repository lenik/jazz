package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.nio.file.Path;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.AbstractXjdocProvider;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.model.ClassDoc;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;

public class MavenXjdocProvider
        extends AbstractXjdocProvider {

    @Override
    public ClassDoc loadClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
        if (pomDir == null) // Not in a maven project.
            return null;

        Path sourceFile = pomDir.getSourceFile(clazz);
        if (sourceFile == null) // Not belong to the maven project.
            return null;
        if (FileFn.notExists(sourceFile))
            return null;

        ClassLoader classLoader = clazz.getClassLoader();
        ClassDocBuilder classDocBuilder = new ClassDocBuilder(getTagLibrary());

        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        projectBuilder.addClassLoader(classLoader);

        try {
            projectBuilder.addSource(sourceFile.toFile());
        } catch (IOException e) {
            throw new XjdocLoaderException("Failed to add source " + sourceFile, e);
        }

        JavaClass javaClass = projectBuilder.getClassByName(clazz.getName());
        if (javaClass == null)
            throw new XjdocLoaderException("No class source in file: " + sourceFile);

        ClassDoc classDoc;
        try {
            classDoc = classDocBuilder.buildClass(javaClass);
        } catch (ParseException e) {
            throw new XjdocLoaderException("Parse error: " + e.getMessage(), e);
        }
        return classDoc;
    }

    static MavenXjdocProvider instance = new MavenXjdocProvider();

    public static MavenXjdocProvider getInstance() {
        return instance;
    }

}
