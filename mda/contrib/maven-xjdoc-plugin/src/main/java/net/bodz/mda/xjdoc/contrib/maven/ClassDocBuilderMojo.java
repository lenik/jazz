package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.io.IOException;

import javax.free.FinalNegotiation;
import javax.free.ICharOut;
import javax.free.INegotiation;
import javax.free.IStreamOutputTarget;
import javax.free.JavaioFile;
import javax.free.NegotiationException;
import javax.free.NegotiationParameter;
import javax.free.NotImplementedException;
import javax.free.OutputStreamTarget;
import javax.free.UnexpectedException;

import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.meta.JavadocXjLang;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.util.TypeNameContext;

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

    /**
     * The extension name used to generate classdoc resource files.
     * 
     * @parameter expression="${classdoc.extension}"
     */
    String extension = "classdoc";

    /**
     * Xjdoc language name.
     * 
     * @parameter expression="${classdoc.lang}"
     */
    String langName = "javadoc";

    /**
     * Include the test resources.
     * 
     * @parameter
     */
    boolean testResources;

    /**
     * Add template attributes for missing elements.
     * 
     * @parameter expression="${classdoc.missingDoc}"
     */
    String missingDoc;

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

            TypeNameContext sourceFileImports = new TypeNameContext(packageName);
            for (String importFqcn : jsource.getImports())
                sourceFileImports.importTypeName(importFqcn);

            IXjLanguage sourceLang = getLang(langName, sourceFileImports);

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

                TypeNameContext classImports = classDoc.getOrCreateImports();
                IXjLanguage classLang = getLang(langName, classImports);
                INegotiation negotiation = new FinalNegotiation(//
                        new NegotiationParameter(//
                                IXjLanguage.class, classLang // JavadocXjLang.getInstance()
                        ));

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    outTarget = new JavaioFile(classDocFile).toTarget();
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

    // static Map<String, IXjLanguage> langMap;

    public IXjLanguage getLang(String langName, TypeNameContext typeNameContext) {
        if (langName.equals("javadoc"))
            return new JavadocXjLang(typeNameContext);
        else
            throw new NotImplementedException("lang: " + langName);
    }

}
