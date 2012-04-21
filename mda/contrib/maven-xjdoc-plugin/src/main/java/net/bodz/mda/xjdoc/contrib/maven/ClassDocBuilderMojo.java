package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;
import net.bodz.bas.lang.negotiation.FinalNegotiation;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.m2.mojo.AbstractResourceGeneratorMojo;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
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

    /**
     * @parameter
     */
    Class<?> langClass;

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

    /**
     * Xjdoc language name.
     * 
     * This can be the FQCN of the {@link IXjLanguage} implementation, or predefined language name
     * includes:
     * <ul>
     * <li>javadoc
     * </ul>
     * 
     * @parameter expression="${classdoc.lang}" default-value="javadoc"
     */
    public String getLanguage() {
        if (langClass == null)
            return null;
        else
            return langClass.getName();
    }

    public void setLanguage(String langName) {
        if (langName == null)
            throw new NullPointerException("langName");
        try {
            langClass = Class.forName(langName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Language isn't defined: " + langName);
        }
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

            ImportMap sourceFileImports = new ImportMap(packageName);
            for (String importFqcn : jsource.getImports())
                sourceFileImports.add(importFqcn);

            IXjLanguage sourceLang = createLang(sourceFileImports);

            for (JavaClass jclass : jsource.getClasses()) {
                ClassDocBuilder builder = new ClassDocBuilder(sourceLang, sourceFileImports);
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
                IXjLanguage classLang = createLang(classImports);

                INegotiation negotiation = new FinalNegotiation(//
                        new NegotiationParameter(//
                                IXjLanguage.class, classLang // JavadocXjLang.getInstance()
                        ));

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    outTarget = new JavaioFile(classDocFile).getOutputTarget();
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

    IXjLanguage createLang(ImportMap importMap)
            throws MojoExecutionException {
        try {
            IXjLanguage lang = (IXjLanguage) langClass.newInstance();
            lang.negotiate(new NegotiationParameter(ImportMap.class, importMap));
            return lang;
        } catch (ReflectiveOperationException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (NegotiationException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
