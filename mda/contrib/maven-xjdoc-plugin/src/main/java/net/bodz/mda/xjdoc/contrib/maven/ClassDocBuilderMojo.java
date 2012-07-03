package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.ListNegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.Option;
import net.bodz.bas.m2.mojo.AbstractResourceGeneratorMojo;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.meta.ITagBook;
import net.bodz.mda.xjdoc.meta.JavadocTagBook;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.util.ImportMap;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.ClassLibrary;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

/**
 * Builder .classdoc from Java source files.
 * 
 * @goal build
 * @phase generate-resources
 */
public class ClassDocBuilderMojo
        extends AbstractResourceGeneratorMojo {

    String extension = "classdoc";
    ITagBook book = PredefinedBooks.getBook("javadoc");
    DomainString missingDoc;

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

    public ITagBook getBook() {
        return book;
    }

    public void setBook(ITagBook book) {
        this.book = book;
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
    public String getBookName() {
        if (book == null)
            return null;
        else
            return PredefinedBooks.indexOf(book);
    }

    public void setBookName(String bookName) {
        if (bookName == null)
            throw new NullPointerException("bookName");
        ITagBook book = PredefinedBooks.getBook(bookName);
        if (book == null)
            try {
                Class<? extends ITagBook> bookClass = (Class<? extends ITagBook>) Class.forName(bookName);
                book = bookClass.newInstance();
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Book isn't defined: " + bookName);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed to instantiate book: " + bookName, e);
            }
        this.book = book;
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
                ClassDocBuilder builder = new ClassDocBuilder(book);
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

                INegotiation negotiation = new ListNegotiation(//
                        new Option(ITagBook.class, book), //
                        new Option(ImportMap.class, classImports));

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
