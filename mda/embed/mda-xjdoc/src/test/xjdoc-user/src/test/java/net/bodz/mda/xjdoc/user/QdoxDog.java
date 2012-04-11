package net.bodz.mda.xjdoc.user;

import java.io.File;
import java.io.StringWriter;

import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.ClassDocBuilder;
import net.bodz.mda.xjdoc.user.xjl.AnimalXjLang;

import com.bee32.plover.xutil.m2.MavenPath;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;

public class QdoxDog {

    public static void main(String[] args)
            throws Exception {
        ClassLoader scl = ClassLoader.getSystemClassLoader();

        ClassLibrary syslib = new ClassLibrary(scl);
        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(syslib);

        File animalSource = MavenPath.getSourceFile(Animal.class);
        File dogSource = MavenPath.getSourceFile(Dog.class);
        javaDocBuilder.addSource(animalSource);
        javaDocBuilder.addSource(dogSource);

        for (JavaClass jclass : javaDocBuilder.getClasses()) {
            IXjLanguage lang; // = JavadocXjLang.getInstance();
            lang = new AnimalXjLang();
            ClassDocBuilder builder = new ClassDocBuilder(lang);
            ClassDoc classdoc = builder.buildClass(jclass);

            StringWriter buf = new StringWriter();
            FlatfOutput ffout = new FlatfOutput(buf);
            classdoc.writeObject(ffout);
            String ff = buf.toString();
            System.out.println("CLASS: " + jclass.getFullyQualifiedName());
            System.out.println(ff + " +++");
        }
    }

}
