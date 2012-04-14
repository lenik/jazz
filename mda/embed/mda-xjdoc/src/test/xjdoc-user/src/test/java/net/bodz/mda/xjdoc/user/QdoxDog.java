package net.bodz.mda.xjdoc.user;

import java.io.File;
import java.io.StringWriter;

import javax.free.FinalNegotiation;
import javax.free.INegotiation;
import javax.free.NegotiationParameter;
import javax.free.StringSource;

import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.model.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.user.xjl.AnimalXjLang;
import net.bodz.mda.xjdoc.util.TypeNameContext;

import com.bee32.plover.xutil.m2.MavenPath;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

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

        for (JavaSource jsource : javaDocBuilder.getSources()) {
            TypeNameContext sharedContext = new TypeNameContext(jsource.getPackageName());
            for (String importFqcn : jsource.getImports())
                sharedContext.importTypeName(importFqcn);

            for (JavaClass jclass : jsource.getClasses()) {
                IXjLanguage lang = new AnimalXjLang(sharedContext);

                INegotiation negotiation = new FinalNegotiation(//
                        new NegotiationParameter(//
                                IXjLanguage.class, lang // JavadocXjLang.getInstance()
                        ));

                ClassDocBuilder builder = new ClassDocBuilder(lang, sharedContext);
                ClassDoc classdoc = builder.buildClass(jclass);

                StringWriter buf = new StringWriter();
                FlatfOutput ffout = new FlatfOutput(buf);
                classdoc.writeObject(ffout, negotiation);
                String ff = buf.toString();
                System.out.println("CLASS: " + jclass.getFullyQualifiedName());
                System.out.println(ff + " ---");

                StringSource ffSource = new StringSource(ff);
                ClassDocLoader docLoader = new ClassDocLoader(lang);
                ClassDoc doc2 = docLoader.load(jclass.getFullyQualifiedName(), ffSource);

                StringWriter buf2 = new StringWriter();
                FlatfOutput ffout2 = new FlatfOutput(buf2);
                doc2.writeObject(ffout2, negotiation);
                String ff2 = buf.toString();
                System.out.println("CLASS2: " + jclass.getFullyQualifiedName());
                System.out.println(ff2 + " ===");
            }
        }
    }

}
