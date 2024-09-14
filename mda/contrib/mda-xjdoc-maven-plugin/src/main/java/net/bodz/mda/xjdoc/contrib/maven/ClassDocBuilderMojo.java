package net.bodz.mda.xjdoc.contrib.maven;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;

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
import com.thoughtworks.qdox.directorywalker.DirectoryScanner;
import com.thoughtworks.qdox.directorywalker.FileVisitor;
import com.thoughtworks.qdox.directorywalker.Filter;
import com.thoughtworks.qdox.directorywalker.SuffixFilter;
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
     * @parameter property="${classdoc.tests}"
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

    boolean alwaysMake = false;

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

    ClassLoader classLoader;
    ITagLibrary tagLibrary;

    synchronized ClassLoader getClassLoader()
            throws MojoFailureException {
        if (classLoader == null) {
            try {
                classLoader = MavenProjects.createRuntimeClassLoader(getProject());
            } catch (DependencyResolutionRequiredException e) {
                throw new MojoFailureException(e.getMessage(), e);
            }
        }
        return classLoader;
    }

    public ITagLibrary getTagLibrary()
            throws MojoFailureException {
        if (tagLibrary == null) {
            ClassLoader loader = getClassLoader();
            TagLibrarySet tagLibs = new TagLibraryLoader(loader).parseSet(taglibNames);
            tagLibrary = tagLibs;
        }
        return tagLibrary;
    }

//    PrintStream dbg;

    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        // File diagFile = new File("/xxx/dbg");
        DurationTimer duration = new DurationTimer().start();

        // try (PrintStream dbg = new PrintStream(new FileOutputStream(diagFile, true))) {
        File srcRoot = getSourceDirectory();
        File outRoot = getOutputDirectory();

        log.debug("make classdoc in:" + srcRoot);

        if (srcRoot == null)
            throw new IllegalUsageException("srcRoot");
        if (outRoot == null)
            throw new IllegalUsageException("outRoot");

        Collection<JavaSource> jsources = findSources(srcRoot, outRoot);

        int nUpdates = make(outRoot, jsources);

        duration.end();
        log.debug("          duration " + duration.getDurationMicros() + " us, " + nUpdates + " sources");
//        } catch (IOException e) {
//            throw new RuntimeException("diag print error: " + e.getMessage(), e);
//        }
    }

    Collection<JavaSource> findSources(File srcDir, File dstDir)
            throws MojoFailureException {
        Log log = getLog();

        JavaProjectBuilder projectBuilder = new JavaProjectBuilder();
        projectBuilder.addClassLoader(getClassLoader());

        log.info("Search javadocs to be updated in: " + srcDir);
        DirectoryScanner scanner = new DirectoryScanner(srcDir);
        scanner.addFilter(new SuffixFilter(".java"));

        if (! alwaysMake)
            scanner.addFilter(new IgnoreUpdatedFilter(srcDir, dstDir));

        scanner.scan(new FileVisitor() {
            @Override
            public void visitFile(File currentFile) {
                try {
                    projectBuilder.addSource(currentFile);
                } catch (IOException e) {
                    throw new RuntimeException("Cannot read file : " + currentFile.getName());
                }
            }
        });

        Collection<JavaSource> jsources = projectBuilder.getSources();
        return jsources;
    }

    class IgnoreUpdatedFilter
            implements
                Filter {

        File srcDir;
        File dstDir;
        int srcDirLen;

        public IgnoreUpdatedFilter(File srcDir, File dstDir) {
            this.srcDir = srcDir;
            this.dstDir = dstDir;
            srcDirLen = srcDir.getPath().length();
        }

        @Override
        public boolean filter(File srcFile) {
            String srcPath = srcFile.getPath();
            int srcPrefixLen = srcDirLen + 1;
            if (srcPath.length() < srcPrefixLen) // dir, not a file.
                return false;
            String srcRelativePath = srcPath.substring(srcPrefixLen);
            if (! srcRelativePath.endsWith(".java"))
                return false;
            String dstRelativePath = srcRelativePath.substring(0, srcRelativePath.length() - 5) + ".ff";
            File dstFile = new File(dstDir, dstRelativePath);

            if (dstFile.exists()) {
                long d = dstFile.lastModified() - srcFile.lastModified();
                if (d > 0) // unchanged, don't build again.
                    return false;
            }
            return true;
        }

    }

    int make(File outRoot, Collection<JavaSource> jsources)
            throws MojoExecutionException, MojoFailureException {

        Log log = getLog();
        log.info("Generating class docs for " + jsources.size() + " source files");
        int count = 0;

        for (JavaSource jsource : jsources) {
            String packageName = jsource.getPackageName();
            File outDir = outRoot == null ? null : new File(outRoot, packageName.replace('.', '/'));

            // Not used: souce imports are resolvable in members.
            // ImportMap sourceFileImports = new ImportMap(packageName);
            // for (String importFqcn : jsource.getImports())
            // sourceFileImports.add(importFqcn);

            for (JavaClass jclass : QdoxUtils.getAllNestedClasses(jsource)) {
                String qName = jclass.getFullyQualifiedName();
                if (! qName.startsWith(packageName + "."))
                    throw new UnexpectedException("Class FQCN doesn't starts with package: " + qName);
                String baseName = qName.substring(packageName.length() + 1);
                baseName = baseName.replace('.', '$') + "." + extension;
                File classDocFile = outDir == null ? null : new File(outDir, baseName);
                make(jclass, classDocFile);
            } // for class
            count++;
        } // for source

        return count;
    }

    void make(JavaClass jclass, File classDocFile)
            throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        ITagLibrary tagLibrary = getTagLibrary();

        ClassDocBuilder builder = new ClassDocBuilder(tagLibrary);
        // builder.setCreateClassImports(true);
        ClassDoc classDoc;
        try {
            classDoc = builder.buildClass(jclass);
        } catch (ParseException e) {
            throw new MojoFailureException(e.getMessage(), e);
        }
        // builder.setMissingDoc(missingDoc);

        ImportMap classImports = classDoc.getOrCreateImports();

        IOptions options = new Options() //
                .addOption(ITagLibrary.class, tagLibrary) //
                .addOption(ImportMap.class, classImports) //
                .addOption(Log.class, log) //
                .addOption("log", (Consumer<String>) a -> log.info("ff: " + a));

        IStreamOutputTarget outTarget;
        if (classDocFile == null) {
            outTarget = new OutputStreamTarget(System.out);
            System.out.println("FILE: " + classDocFile.getName());
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
    }

}
