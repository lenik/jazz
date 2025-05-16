package net.bodz.bas.program.skel;

import java.nio.file.Path;
import java.util.ServiceLoader;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.fmt.flatf.FlatfOutput;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.ListOptions;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.conv.FlatfXjdocProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;

import com.thoughtworks.qdox.JavaProjectBuilder;
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

        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        projectBuilder.addClassLoader(scl);

        ITagLibrary tagLibrary = Xjdocs.getDefaultTagLibrary();

        MavenPomDir pomDir = MavenPomDir.fromClass(BasicCLI.class);

        Path sourceFile = pomDir.getSourceFile(BasicCLI.class);

        projectBuilder.addSource(sourceFile.toFile());
        // javaDocBuilder.addSourceTree(file)

        for (JavaSource jsource : projectBuilder.getSources()) {
            // String packageName = jsource.getPackageName();

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(tagLibrary);
                ClassDoc classDoc = builder.buildClass(jclass);

                String fqcn = jclass.getFullyQualifiedName();
                ImportMap builderMap = classDoc.getOrCreateImports();

                IOptions n_ffout = new ListOptions() //
                        .addOption(ITagLibrary.class, tagLibrary) //
                        .addOption(ImportMap.class, builderMap);

                BCharOut buf = new BCharOut();
                FlatfOutput ffout = new FlatfOutput(buf);
                classDoc.writeObject(ffout, n_ffout);
                String ff = buf.toString();
                System.out.println("CLASS: " + fqcn);
                System.out.println(ff + " ---");

                FlatfXjdocProvider ffLoader = new FlatfXjdocProvider();
                ffLoader.setTagLibrary(tagLibrary);
                ClassDoc doc2 = ffLoader.loadText(fqcn, ff);

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
