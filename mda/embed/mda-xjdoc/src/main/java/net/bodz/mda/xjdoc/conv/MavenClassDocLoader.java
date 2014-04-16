package net.bodz.mda.xjdoc.conv;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.mda.xjdoc.ClassDocLoadException;
import net.bodz.mda.xjdoc.IClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;

public class MavenClassDocLoader
        implements IClassDocLoader {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public ClassDoc load(Class<?> clazz)
            throws ClassDocLoadException {
        MavenPomDir pomDir = MavenPomDir.fromClass(clazz);
        File sourceFile = pomDir.getSourceFile(clazz);
        if (sourceFile == null)
            return null;

        ClassLoader classLoader = clazz.getClassLoader();
        TagLibrarySet taglibs = TagLibraryLoader.allFor(classLoader);
        ClassDocBuilder classDocBuilder = new ClassDocBuilder(taglibs);

        ClassLibrary classLibrary = new ClassLibrary(classLoader);
        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(classLibrary);

        try {
            javaDocBuilder.addSource(sourceFile);
        } catch (IOException e) {
            throw new ClassDocLoadException("Failed to add source " + sourceFile, e);
        }

        JavaClass javaClass = javaDocBuilder.getClassByName(clazz.getName());
        if (javaClass == null)
            throw new ClassDocLoadException("No class source in file: " + sourceFile);

        ClassDoc classDoc = classDocBuilder.buildClass(javaClass);
        return classDoc;
    }

}
