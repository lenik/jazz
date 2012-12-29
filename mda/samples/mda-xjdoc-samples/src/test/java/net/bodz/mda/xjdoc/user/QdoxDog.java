package net.bodz.mda.xjdoc.user;

import static net.bodz.bas.rtx.Negotiation.list;
import static net.bodz.bas.rtx.Negotiation.option;

import java.io.File;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenProjectOrigin;
import net.bodz.bas.io.resource.builtin.StringSource;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.conv.ClassDocFlatfLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryManager;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;
import net.bodz.mda.xjdoc.user.xjl.AnimalTagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;

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

        TagLibraryManager.register("animal", new AnimalTagLibrary());
        TagLibrarySet taglibs = TagLibraryManager.parseSet("javadoc, animal");

        MavenProjectOrigin po = MavenProjectOrigin.fromClass(Animal.class);

        File animalSource = po.getSourceFile(Animal.class);
        File dogSource = po.getSourceFile(Dog.class);

        javaDocBuilder.addSource(animalSource);
        javaDocBuilder.addSource(dogSource);
        // javaDocBuilder.addSourceTree(file)

        for (JavaSource jsource : javaDocBuilder.getSources()) {
            // String packageName = jsource.getPackageName();

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(taglibs);
                ClassDoc classDoc = builder.buildClass(jclass);

                String fqcn = jclass.getFullyQualifiedName();
                ImportMap builderMap = classDoc.getOrCreateImports();

                INegotiation n_ffout = list(//
                        option(ITagLibrary.class, taglibs), //
                        option(ImportMap.class, builderMap));

                BCharOut buf = new BCharOut();
                FlatfOutput ffout = new FlatfOutput(buf);
                classDoc.writeObject(ffout, n_ffout);
                String ff = buf.toString();
                System.out.println("CLASS: " + fqcn);
                System.out.println(ff + " ---");

                StringSource ffSource = new StringSource(ff);

                ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(taglibs);
                ClassDoc doc2 = ffLoader.load(fqcn, ffSource);

                BCharOut buf2 = new BCharOut();
                FlatfOutput ffout2 = new FlatfOutput(buf2);
                doc2.writeObject(ffout2, n_ffout);
                String ff2 = buf.toString();
                System.out.println("CLASS2: " + fqcn);
                System.out.println(ff2 + " ===");

                assertEquals(ff, ff2);
            }
        }
    }

}
