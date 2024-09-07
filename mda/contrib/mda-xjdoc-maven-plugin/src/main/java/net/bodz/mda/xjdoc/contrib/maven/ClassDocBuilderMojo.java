package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.util.Collection;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.flatf.FlatfOutput;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.OutputStreamTarget;
import net.bodz.bas.m2.util.MavenProjects;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.mda.xjdoc.conv.ClassDocBuilder;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.javadoc.JavadocTagLibrary;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;
import net.bodz.mda.xjdoc.util.ImportMap;
import net.bodz.mda.xjdoc.util.QdoxUtils;
import net.bodz.shared.mojo.AbstractResourceGeneratorMojo;

import com.thoughtworks.qdox.JavaProjectBuilder;
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

    String version = "1.0";

    /**
     * Include test classes.
     *
     * @parameter expression="${classdoc.tests}"
     */
    boolean testClasses;

    /**
     * The extension name used to generate classdoc resource files.
     *
     * @parameter property="classdoc.extension"
     */
    String extension = "ff";

    /**
     * Add template attributes for missing elements.
     *
     * @parameter property="classdoc.missingDoc"
     */
    iString missingDoc;

    /**
     * Xjdoc taglib names.
     *
     * This can be the FQCN of the {@link ITagLibrary} implementation, or predefined language name
     * includes:
     * <ul>
     * <li>javadoc: {@link JavadocTagLibrary}
     * </ul>
     *
     * @parameter property="classdoc.taglibs" default-value="*"
     */
    String taglibNames = "*";

    TagLibraryLoader _taglibLoader;

    public ClassDocBuilderMojo() {
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

    public void setMissingDoc(String missingDoc)
            throws ParseException {
        this.missingDoc = MultiLangStrings.parse(missingDoc);
    }

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {
        Log log = getLog();

        MavenProject project = getProject();

        ClassLoader runtimeClassLoader;
        try {
            runtimeClassLoader = MavenProjects.createRuntimeClassLoader(project);
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoFailureException(e.getMessage(), e);
        }

        TagLibraryLoader taglibLoader = new TagLibraryLoader(runtimeClassLoader);
        TagLibrarySet taglibs = taglibLoader.parseSet(taglibNames);

        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        projectBuilder.addClassLoader(runtimeClassLoader);

        File srcRoot = getSourceDirectory();
        File outRoot = getOutputDirectory();

        if (srcRoot == null)
            throw new IllegalUsageException("srcRoot");
        if (outRoot == null)
            throw new IllegalUsageException("outRoot");

        log.info("Search javadocs in: " + srcRoot);
        projectBuilder.addSourceTree(srcRoot);

        Collection<JavaSource> jsources = projectBuilder.getSources();
        log.info("Generating class docs for " + jsources.size() + " source files");

        for (JavaSource jsource : jsources) {
            String packageName = jsource.getPackageName();
            File outDir = outRoot == null ? null : new File(outRoot, packageName.replace('.', '/'));

            // Not used: souce imports are resolvable in memeber members.
            // ImportMap sourceFileImports = new ImportMap(packageName);
            // for (String importFqcn : jsource.getImports())
            // sourceFileImports.add(importFqcn);

            for (JavaClass jclass : QdoxUtils.getAllNestedClasses(jsource)) {
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

                IOptions options = new Options() //
                        .addOption(ITagLibrary.class, taglibs) //
                        .addOption(ImportMap.class, classImports);

                IStreamOutputTarget outTarget;
                if (classDocFile == null) {
                    outTarget = new OutputStreamTarget(System.out);
                    System.out.println("FILE: " + baseName);
                } else {
                    log.debug("Generate " + classDocFile);
                    classDocFile.getParentFile().mkdirs();
                    outTarget = ResFn.file(classDocFile);
                }
                // outTarget.setCharset("utf-8");
                try {
                    ICharOut charOut = outTarget.newCharOut();
                    FlatfOutput ffOut = new FlatfOutput(charOut);
                    ffOut.comment("version: " + version);
                    classDoc.writeObject(ffOut, options);
                    charOut.flush();
                } catch (Exception e) {
                    throw new MojoExecutionException(e.getMessage(), e);
                }
            } // for class
        } // for source
    }

}
