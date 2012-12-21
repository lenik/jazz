package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Manifest;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.io.FileZip;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public class JavaProject
        extends EclipseProject {

    File defaultOutput;
    List<JavaProjectSourceFolder> sourceFolders = new ArrayList<JavaProjectSourceFolder>();
    List<JavaProject> projectRefs = new ArrayList<JavaProject>();
    List<JavaProjectLibraryRef> libraryRefs = new ArrayList<JavaProjectLibraryRef>();

    String[] searchSourceSuffixes;

    JavaProject() {
        super();
    }

    public JavaProject(File baseDir)
            throws IOException, ParseException {
        super(baseDir);

        File classpathFile = new File(baseDir, ".classpath");
        File manifestFile = new File(baseDir, "META-INF/MANIFEST.MF");

        if (classpathFile.exists())
            try {
                XMLReader xmlReader = XMLReaderFactory.createXMLReader();

                ClasspathFileContentHandler handler = new ClasspathFileContentHandler(this);
                xmlReader.setContentHandler(handler);

                InputSource inputSource = new InputSource(FileURL.toURL(classpathFile).toString());

                xmlReader.parse(inputSource);
            } catch (SAXException e) {
                throw new ParseException(e);
            }

        if (manifestFile.exists()) {
            Manifest manifest = new Manifest(new FileInputStream(manifestFile));

            java.util.jar.Attributes attributes = manifest.getMainAttributes();

            String requireBundles = (String) attributes.get("Require-Bundle");
            if (requireBundles != null) {
                for (String requireBundle : requireBundles.split(",")) {
                    String bundleName;
                    int semic = requireBundle.indexOf(';');
                    if (semic == -1)
                        bundleName = requireBundle;
                    else
                        bundleName = requireBundle.substring(0, semic);
                }
                // find the bundle...
            }
        }
    }

    public static JavaProject forClass(Class<?> clazz) {
        JavaProject project = new JavaProject();

        File rootFile = ClassResource.getRootFile(clazz);
        JavaProjectLibraryRef main = new JavaProjectLibraryRef();
        main.path = rootFile;

        project.addLibraryRef(main);
        return project;
    }

    public File getDefaultOutput() {
        return defaultOutput;
    }

    public void setDefaultOutput(File defaultOutput) {
        this.defaultOutput = defaultOutput;
    }

    public List<JavaProjectSourceFolder> getSourceFolders() {
        return Collections.unmodifiableList(sourceFolders);
    }

    public void addSourceFolder(JavaProjectSourceFolder sourceFolder) {
        sourceFolders.add(sourceFolder);
    }

    public List<JavaProject> getProjectRefs() {
        return Collections.unmodifiableList(projectRefs);
    }

    public void addProjectRef(JavaProject project) {
        projectRefs.add(project);
    }

    public List<JavaProjectLibraryRef> getLibraryRefs() {
        return Collections.unmodifiableList(libraryRefs);
    }

    public void addLibraryRef(JavaProjectLibraryRef library) {
        this.libraryRefs.add(library);
    }

    public JavaProjectSourceFolder findSourceFolder(Class<?> clazz) {
        JavaProjectBuildPathEntry buildPathEntry = findBuildPathEntry(clazz);
        if (buildPathEntry == null)
            return null;

        File javaFile = buildPathEntry.getSourcePath();
        if (javaFile == null)
            return null;

        return findSourceFolder(javaFile);
    }

    public JavaProjectSourceFolder findSourceFolder(File sourceFile) {
        sourceFile = FilePath.canoniOf(sourceFile);
        for (JavaProjectSourceFolder sourceFolder : sourceFolders) {
            if (FileRelation.isChildOf(sourceFile, sourceFolder.path))
                return sourceFolder;
        }
        return null;
    }

    public URL findSourceURL(Class<?> clazz) {
        String fqcn = clazz.getName();
        String fqcn_java = StringPart.beforeLast(fqcn, '$', fqcn).replace('.', '/') + ".java";

        JavaProjectBuildPathEntry buildPathEntry = findBuildPathEntry(clazz);
        if (buildPathEntry == null)
            return null;

        URL parentURL = buildPathEntry.getSourceURL();
        if (parentURL == null)
            return null;

        switch (parentURL.getProtocol()) {
        case "file":
            File parentFile = FileURL.toFile(parentURL, null);
            if (parentFile.isDirectory()) {
                File javaFile = new File(parentFile, fqcn_java);
                if (!javaFile.exists())
                    throw new RuntimeException("java source file is missing: " + javaFile);
                return FileURL.toURL(javaFile);
            }
            break;

        case "jar":
            String embeddedURL = "jar:" + parentURL + "!/" + fqcn_java;
            try {
                return new URL(embeddedURL);
            } catch (MalformedURLException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        }
        return null;
    }

    public JavaProjectBuildPathEntry findBuildPathEntry(Class<?> clazz) {
        return findBuildPathEntry(clazz.getName());
    }

    public JavaProjectBuildPathEntry findBuildPathEntry(String fqcn) {
        String fqcn_class = fqcn.replace('.', '/') + ".class";
        String fqcn_java = fqcn.replace('.', '/') + ".java";

        if (defaultOutput == null) {
            for (JavaProjectSourceFolder sourceFolder : sourceFolders)
                if (isUnder(fqcn_class, sourceFolder.output))
                    return new JavaProjectBuildPathEntry(sourceFolder, defaultOutput);

            for (JavaProjectLibraryRef library : libraryRefs)
                if (isUnder(fqcn_class, library.path))
                    return new JavaProjectBuildPathEntry(library);

            return null;
        }

        if (isUnder(fqcn_class, defaultOutput)) {
            JavaProjectSourceFolder foundSourceFolder = null;
            for (JavaProjectSourceFolder sourceFolder : sourceFolders)
                if (isUnder(fqcn_java, sourceFolder.path)) {
                    foundSourceFolder = sourceFolder;
                    break;
                }
            if (foundSourceFolder != null)
                return new JavaProjectBuildPathEntry(foundSourceFolder, defaultOutput);
            else
                // none of source directories contains the source, try heuristic
                return new JavaProjectBuildPathEntry(null, defaultOutput, searchSourceSuffixes);
        }

        return null;
    }

    static boolean isUnder(String childName, File parent) {
        if (childName == null)
            throw new NullPointerException("childName");
        if (parent == null)
            throw new NullPointerException("parent");

        if (!parent.exists())
            return false;

        if (parent.isDirectory())
            return new File(parent, childName).exists();

        if (parent.isFile())
            try {
                if (FileZip.containsAnyEntry(parent, childName))
                    return true;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        return false;
    }

    public List<File> getClasspath()
            throws ParseException {
        List<File> list = new ArrayList<File>();
        if (defaultOutput != null)
            list.add(defaultOutput);
        for (JavaProjectSourceFolder source : sourceFolders)
            if (source.output != null)
                list.add(source.output);
        for (JavaProjectLibraryRef l : libraryRefs)
            list.add(l.path);
        return list;
    }

    /**
     * Convert classpaths to URL[].
     */
    public URL[] getURLClasspath()
            throws ParseException {
        List<File> classpaths = getClasspath();
        URL[] urls = new URL[classpaths.size()];
        int i = 0;
        for (File classpath : classpaths) {
            URL url = FileURL.toURL(classpath, null);
            urls[i++] = url;
        }
        return urls;
    }

}
