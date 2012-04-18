package net.bodz.mda.xjdoc.user;

import java.io.File;

import javax.free.BCharOut;
import javax.free.FinalNegotiation;
import javax.free.INegotiation;
import javax.free.NegotiationParameter;
import javax.free.StringSource;

import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.conv.ClassDocFlatfLoader;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.user.xjl.AnimalXjLang;
import net.bodz.mda.xjdoc.util.ImportMap;

import org.junit.Assert;

import com.bee32.plover.xutil.m2.MavenPath;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

public class QdoxDog
        extends Assert {

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
            String packageName = jsource.getPackageName();
            ImportMap sourceFileImports = new ImportMap(packageName);
            for (String importFqcn : jsource.getImports())
                sourceFileImports.add(importFqcn);

            for (JavaClass jclass : jsource.getClasses()) {
                IXjLanguage lang = new AnimalXjLang();
                lang.negotiate(new NegotiationParameter(ImportMap.class, sourceFileImports));

                ClassDocBuilder builder = new ClassDocBuilder(lang, sourceFileImports);
                // builder.setCreateClassImports(true);
                ClassDoc classDoc = builder.buildClass(jclass);

                String fqcn = jclass.getFullyQualifiedName();

                ImportMap classImports = classDoc.getOrCreateImports();
                lang = new AnimalXjLang();
                lang.negotiate(new NegotiationParameter(ImportMap.class, classImports));

                INegotiation negotiation = new FinalNegotiation(//
                        new NegotiationParameter(//
                                IXjLanguage.class, lang // JavadocXjLang.getInstance()
                        ));

                BCharOut buf = new BCharOut();
                FlatfOutput ffout = new FlatfOutput(buf);
                classDoc.writeObject(ffout, negotiation);
                String ff = buf.toString();
                System.out.println("CLASS: " + fqcn);
                System.out.println(ff + " ---");

                StringSource ffSource = new StringSource(ff);

                classImports = new ImportMap(sourceFileImports, packageName);
                classDoc.setImports(classImports);
                IXjLanguage lang2 = new AnimalXjLang();
                lang2.negotiate(new NegotiationParameter(ImportMap.class, classImports));

                ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(lang2);
                ClassDoc doc2 = ffLoader.load(fqcn, ffSource);
                doc2.setImports(classImports);

                BCharOut buf2 = new BCharOut();
                FlatfOutput ffout2 = new FlatfOutput(buf2);
                doc2.writeObject(ffout2, negotiation);
                String ff2 = buf.toString();
                System.out.println("CLASS2: " + fqcn);
                System.out.println(ff2 + " ===");

                assertEquals(ff, ff2);
            }
        }
    }

}
