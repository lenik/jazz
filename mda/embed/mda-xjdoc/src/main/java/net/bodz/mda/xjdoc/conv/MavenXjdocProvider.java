package net.bodz.mda.xjdoc.conv;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.mda.xjdoc.AbstractXjdocProvider;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.model.ClassDoc;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;

public class MavenXjdocProvider
        extends AbstractXjdocProvider {

    @Override
    public ClassDoc getClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
        if (pomDir == null) // Not in a maven project.
            return null;

        File sourceFile = pomDir.getSourceFile(clazz);
        if (sourceFile == null) // Not belong to the maven project.
            return null;

        ClassLoader classLoader = clazz.getClassLoader();
        ClassDocBuilder classDocBuilder = new ClassDocBuilder(getTagLibrary());

        ClassLibrary classLibrary = new ClassLibrary(classLoader);
        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(classLibrary);

        try {
            javaDocBuilder.addSource(sourceFile);
        } catch (IOException e) {
            throw new XjdocLoaderException("Failed to add source " + sourceFile, e);
        }

        JavaClass javaClass = javaDocBuilder.getClassByName(clazz.getName());
        if (javaClass == null)
            throw new XjdocLoaderException("No class source in file: " + sourceFile);

        ClassDoc classDoc = classDocBuilder.buildClass(javaClass);
        return classDoc;
    }

}
