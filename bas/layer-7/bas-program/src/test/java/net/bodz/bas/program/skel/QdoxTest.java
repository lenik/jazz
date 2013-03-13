package net.bodz.bas.program.skel;

import static net.bodz.bas.rtx.Negotiation.list;
import static net.bodz.bas.rtx.Negotiation.option;

import java.io.File;
import java.util.ServiceLoader;

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
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;
import net.bodz.mda.xjdoc.util.ImportMap;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

/**
 * A qdox application, dump classdoc of the Dog class using qdox.
 */
public class QdoxTest
        extends Assert {

    /**
     * Main method.
     */
    public static void main(String[] args)
            throws Exception {

        ClassLoader scl = ClassLoader.getSystemClassLoader();

        for (ITagLibrary taglib : ServiceLoader.load(ITagLibrary.class))
            System.out.println(taglib);

        // qdox ClassLibrary.
        ClassLibrary syslib = new ClassLibrary(scl);
        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(syslib);

        TagLibraryLoader taglibLoader = new TagLibraryLoader(scl);
        TagLibrarySet taglibs = taglibLoader.parseSet("*");

        MavenProjectOrigin po = MavenProjectOrigin.fromClass(BasicCLI.class);

        File sourceFile = po.getSourceFile(BasicCLI.class);

        javaDocBuilder.addSource(sourceFile);
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
