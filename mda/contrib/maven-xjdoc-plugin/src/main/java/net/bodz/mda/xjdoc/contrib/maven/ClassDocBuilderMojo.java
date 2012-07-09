package net.bodz.mda.xjdoc.contrib.maven;

import static net.bodz.bas.lang.negotiation.Negotiation.*;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.m2.mojo.AbstractResourceGeneratorMojo;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.tags.JavadocTagBook;
import net.bodz.mda.xjdoc.tags.MergedTagBook;
import net.bodz.mda.xjdoc.tags.TagBooks;
import net.bodz.mda.xjdoc.util.ImportMap;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

/**
 * Generate .classdoc files from Java source files.
 * 
 * @goal build
 * @phase generate-resources
 * @configurator include-project-dependencies
 */
public class ClassDocBuilderMojo
        extends AbstractResourceGeneratorMojo {

    String extension = "classdoc";
    MergedTagBook mergedBook;
    DomainString missingDoc;

    public ClassDocBuilderMojo() {
        mergedBook = new MergedTagBook();
        setBooks("javadoc");
    }

    /**
     * The extension name used to generate classdoc resource files.
     * 
     * @parameter expression="${classdoc.extension}"
     */
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Add template attributes for missing elements.
     * 
     * @parameter expression="${classdoc.missingDoc}"
     */
    public String getMissingDoc() {
        if (missingDoc == null)
            return null;
        else
            return missingDoc.toMultiLangString();
    }

    public void setMissingDoc(String missingDoc) {
        this.missingDoc = DomainString.parseMultiLangString(missingDoc);
    }

    /**
     * Xjdoc language name.
     * 
     * This can be the FQCN of the {@link ITagBook} implementation, or predefined language name
     * includes:
     * <ul>
     * <li>javadoc: {@link JavadocTagBook}
     * </ul>
     * 
     * @parameter expression="${classdoc.book}" default-value="javadoc"
     */
    public String getBooks() {
        StringBuilder sb = null;
        for (ITagBook book : mergedBook) {
            if (sb == null)
                sb = new StringBuilder();
            else
                sb.append(", ");
            String bookName = TagBooks.getName(book);
            sb.append(bookName);
        }
        return sb.toString();
    }

    public synchronized void setBooks(String bookNames) {
        mergedBook = TagBooks.parse(bookNames);
    }

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLibrary classLibrary = new ClassLibrary(classLoader);

        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(classLibrary);

        File srcRoot = getSourceDirectory();
        File outRoot = getOutputDirectory();
        javaDocBuilder.addSourceTree(srcRoot);

        for (JavaSource jsource : javaDocBuilder.getSources()) {
            String packageName = jsource.getPackageName();
            File outDir = outRoot == null ? null : new File(outRoot, packageName.replace('.', '/'));

            // Not used: souce imports are resolvable in memeber members.
            // ImportMap sourceFileImports = new ImportMap(packageName);
            // for (String importFqcn : jsource.getImports())
            // sourceFileImports.add(importFqcn);

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(mergedBook);
                // builder.setCreateClassImports(true);
                ClassDoc classDoc = builder.buildClass(jclass);
                // builder.setMissingDoc(missingDoc);

                String fqcn = jclass.getFullyQualifiedName();
                if (!fqcn.startsWith(packageName + "."))
                    throw new UnexpectedException("Class FQCN doesn't starts with package: " + fqcn);
                String baseName = fqcn.substring(packageName.length() + 1);
                baseName = baseName.replace('.', '$') + "." + extension;
                File classDocFile = outDir == null ? null : new File(outDir, baseName);

                ImportMap classImports = classDoc.getOrCreateImports();

                INegotiation negotiation = list(//
                        option(ITagBook.class, mergedBook), //
                        option(ImportMap.class, classImports));

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    JavaioFile _file = new JavaioFile(classDocFile);
                    _file.setAutoCreateParents(true);
                    outTarget = _file.getOutputTarget();
                }
                // outTarget.setCharset("utf-8");
                try {
                    ICharOut charOut = outTarget.newCharOut();
                    FlatfOutput ffOut = new FlatfOutput(charOut);
                    classDoc.writeObject(ffOut, negotiation);
                    charOut.flush();
                } catch (IOException e) {
                    throw new MojoExecutionException(e.getMessage(), e);
                } catch (NegotiationException e) {
                    throw new MojoExecutionException(e.getMessage(), e);
                }
            }
        }
    }

}
