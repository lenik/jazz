package net.bodz.mda.xjdoc.user;

import static net.bodz.bas.lang.negotiation.Negotiation.*;

import java.io.File;

import net.bodz.bas.c.misc.MavenPath;
import net.bodz.bas.io.resource.builtin.StringSource;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.conv.ClassDocFlatfLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.tags.TagBooks;
import net.bodz.mda.xjdoc.user.xjl.AnimalTagBook;
import net.bodz.mda.xjdoc.util.ImportMap;

import org.junit.Assert;

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

        TagBooks.register("animal", new AnimalTagBook());
        ITagBook book = TagBooks.parse("javadoc, animal");

        File animalSource = MavenPath.getSourceFile(Animal.class);
        File dogSource = MavenPath.getSourceFile(Dog.class);
        javaDocBuilder.addSource(animalSource);
        javaDocBuilder.addSource(dogSource);
        // javaDocBuilder.addSourceTree(file)

        for (JavaSource jsource : javaDocBuilder.getSources()) {
            String packageName = jsource.getPackageName();

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(book);
                ClassDoc classDoc = builder.buildClass(jclass);

                String fqcn = jclass.getFullyQualifiedName();
                ImportMap builderMap = classDoc.getOrCreateImports();

                INegotiation n_ffout = list(//
                        option(ITagBook.class, book), //
                        option(ImportMap.class, builderMap));

                BCharOut buf = new BCharOut();
                FlatfOutput ffout = new FlatfOutput(buf);
                classDoc.writeObject(ffout, n_ffout);
                String ff = buf.toString();
                System.out.println("CLASS: " + fqcn);
                System.out.println(ff + " ---");

                StringSource ffSource = new StringSource(ff);

                ImportMap newmap = new ImportMap(packageName);
                ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(book, newmap);
                ClassDoc doc2 = ffLoader.load(fqcn, ffSource);
                doc2.setImports(newmap);

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
