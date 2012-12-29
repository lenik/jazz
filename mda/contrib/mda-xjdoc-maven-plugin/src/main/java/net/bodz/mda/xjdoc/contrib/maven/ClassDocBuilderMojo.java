package net.bodz.mda.xjdoc.contrib.maven;

import static net.bodz.bas.rtx.Negotiation.list;
import static net.bodz.bas.rtx.Negotiation.option;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.FileResource;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.flatf.FlatfOutput;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagLibrary;
import net.bodz.mda.xjdoc.tags.JavadocTagLibrary;
import net.bodz.mda.xjdoc.tags.TagLibraryManager;
import net.bodz.mda.xjdoc.tags.TagLibrarySet;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.shared.mojo.AbstractResourceGeneratorMojo;

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
    XDomainString missingDoc;

    /**
     * Xjdoc taglib names.
     * 
     * This can be the FQCN of the {@link ITagLibrary} implementation, or predefined language name
     * includes:
     * <ul>
     * <li>javadoc: {@link JavadocTagLibrary}
     * </ul>
     * 
     * @parameter expression="${classdoc.taglibs}" default-value="javadoc"
     */
    TagLibrarySet taglibs;

    public ClassDocBuilderMojo() {
        taglibs = new TagLibrarySet();
        setTaglibs("javadoc");
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
        this.missingDoc = XDomainString.parseMultiLang(missingDoc);
    }

    public String getTaglibs() {
        StringBuilder sb = null;
        for (ITagLibrary taglib : taglibs) {
            if (sb == null)
                sb = new StringBuilder();
            else
                sb.append(", ");
            String name = TagLibraryManager.nameOf(taglib);
            sb.append(name);
        }
        return sb.toString();
    }

    public synchronized void setTaglibs(String taglibNames) {
        // getLog().info("Set-Books: " + taglibNames);
        taglibs = TagLibraryManager.parseSet(taglibNames);
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
                ClassDocBuilder builder = new ClassDocBuilder(taglibs);
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
                        option(ITagLibrary.class, taglibs), //
                        option(ImportMap.class, classImports));

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    log.debug("Generate " + classDocFile);
                    classDocFile.getParentFile().mkdirs();
                    outTarget = new FileResource(classDocFile);
                }
                // outTarget.setCharset("utf-8");
                try {
                    ICharOut charOut = outTarget.newCharOut();
                    FlatfOutput ffOut = new FlatfOutput(charOut);
                    classDoc.writeObject(ffOut, negotiation);
                    charOut.flush();
                } catch (Exception e) {
                    throw new MojoExecutionException(e.getMessage(), e);
                }
            }
        }
    }

}
