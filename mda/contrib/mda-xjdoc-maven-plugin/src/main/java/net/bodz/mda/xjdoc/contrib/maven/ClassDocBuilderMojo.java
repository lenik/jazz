package net.bodz.mda.xjdoc.contrib.maven;

import static net.bodz.bas.lang.negotiation.Negotiation.*;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.tags.JavadocTagBook;
import net.bodz.mda.xjdoc.tags.MergedTagBook;
import net.bodz.mda.xjdoc.tags.TagBooks;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.shared.mojo.AbstractResourceGeneratorMojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

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
 * @requiresDependencyResolution test
 */
public class ClassDocBuilderMojo
        extends AbstractResourceGeneratorMojo {

    /**
     * Include test classes.
     * 
     * @parameter expression="${classdoc.tests}"
     */
    boolean testClasses;

    /**
     * The extension name used to generate classdoc resource files.
     * 
     * @parameter expression="${classdoc.extension}"
     */
    String extension = "ff";

    /**
     * Add template attributes for missing elements.
     * 
     * @parameter expression="${classdoc.missingDoc}"
     */
    DomainString missingDoc;

    /**
     * Xjdoc book names.
     * 
     * This can be the FQCN of the {@link ITagBook} implementation, or predefined language name
     * includes:
     * <ul>
     * <li>javadoc: {@link JavadocTagBook}
     * </ul>
     * 
     * @parameter expression="${classdoc.book}" default-value="javadoc"
     */
    MergedTagBook books;

    public ClassDocBuilderMojo() {
        books = new MergedTagBook();
        setBooks("javadoc");
    }

    public boolean isTestClasses() {
        return testClasses;
    }

    public void setTestClasses(boolean testClasses) {
        // getLog().info("Set-Test-Classes: " + testClasses);
        this.testClasses = testClasses;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        // getLog().info("Set-Extension: " + extension);
        this.extension = extension;
    }

    public String getMissingDoc() {
        if (missingDoc == null)
            return null;
        else
            return missingDoc.toMultiLangString();
    }

    public void setMissingDoc(String missingDoc) {
        // getLog().info("Set-Missing-Doc: " + missingDoc);
        this.missingDoc = DomainString.parseMultiLang(missingDoc);
    }

    public String getBooks() {
        StringBuilder sb = null;
        for (ITagBook book : books) {
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
        // getLog().info("Set-Books: " + bookNames);
        books = TagBooks.parse(bookNames);
    }

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {
        Log log = getLog();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLibrary classLibrary = new ClassLibrary(classLoader);

        JavaDocBuilder javaDocBuilder = new JavaDocBuilder(classLibrary);

        File srcRoot = getSourceDirectory();
        File outRoot = getOutputDirectory();

        if (srcRoot == null)
            throw new IllegalUsageException("srcRoot");
        if (outRoot == null)
            throw new IllegalUsageException("outRoot");

        log.info("Search javadocs in: " + srcRoot);
        javaDocBuilder.addSourceTree(srcRoot);

        JavaSource[] jsources = javaDocBuilder.getSources();
        log.info("Generating class docs for " + jsources.length + " source files");

        for (JavaSource jsource : jsources) {
            String packageName = jsource.getPackageName();
            File outDir = outRoot == null ? null : new File(outRoot, packageName.replace('.', '/'));

            // Not used: souce imports are resolvable in memeber members.
            // ImportMap sourceFileImports = new ImportMap(packageName);
            // for (String importFqcn : jsource.getImports())
            // sourceFileImports.add(importFqcn);

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(books);
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
                        option(ITagBook.class, books), //
                        option(ImportMap.class, classImports));

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    log.debug("Generate " + classDocFile);
                    classDocFile.getParentFile().mkdirs();
                    outTarget = new LocalFileResource(classDocFile);
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
