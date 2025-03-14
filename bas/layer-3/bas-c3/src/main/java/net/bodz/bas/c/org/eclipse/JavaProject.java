package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Manifest;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

    Path defaultOutput;
    List<JavaProjectSourceFolder> sourceFolders = new ArrayList<JavaProjectSourceFolder>();
    List<JavaProject> projectRefs = new ArrayList<JavaProject>();
    List<JavaProjectLibraryRef> libraryRefs = new ArrayList<JavaProjectLibraryRef>();

    String[] searchSourceSuffixes;

    JavaProject() {
        super();
    }

    public JavaProject(Path baseDir)
            throws IOException, ParseException {
        super(baseDir);

        Path classpathFile = baseDir.resolve(".classpath");
        Path manifestFile = baseDir.resolve("META-INF/MANIFEST.MF");

        if (Files.exists(classpathFile))
            try {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                InputSource inputSource = new InputSource(FileURL.toURL(classpathFile).toString());
                ClasspathFileContentHandler handler = new ClasspathFileContentHandler(this);
                parser.parse(inputSource, handler);
            } catch (SAXException | ParserConfigurationException e) {
                throw new ParseException(e.getMessage(), e);
            }

        if (Files.exists(manifestFile)) {
            Manifest manifest = new Manifest(Files.newInputStream(manifestFile));

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

        Path rootFile = ClassResource.getRootPath(clazz);
        JavaProjectLibraryRef main = new JavaProjectLibraryRef();
        main.path = rootFile;

        project.addLibraryRef(main);
        return project;
    }

    public Path getDefaultOutput() {
        return defaultOutput;
    }

    public void setDefaultOutput(Path defaultOutput) {
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

        Path javaFile = buildPathEntry.getSourcePath();
        if (javaFile == null)
            return null;

        return findSourceFolder(javaFile);
    }

    public JavaProjectSourceFolder findSourceFolder(Path sourceFile) {
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

        String protocol = parentURL.getProtocol();
        if ("file".equals(protocol)) {
            File parentFile = FileURL.toFile(parentURL, null);
            if (parentFile.isDirectory()) {
                File javaFile = new File(parentFile, fqcn_java);
                if (!javaFile.exists())
                    throw new RuntimeException("java source file is missing: " + javaFile);
                return FileURL.toURL(javaFile);
            }
        } else if ("jar".equals(protocol)) {
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

    static boolean isUnder(String childName, Path parent) {
        if (childName == null)
            throw new NullPointerException("childName");
        if (parent == null)
            throw new NullPointerException("parent");

        if (!Files.exists(parent))
            return false;

        if (Files.isDirectory(parent))
            return Files.exists(parent.resolve(childName));

        if (Files.isRegularFile(parent))
            try {
                if (FileZip.containsAnyEntry(parent, childName))
                    return true;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        return false;
    }

    public List<Path> getClasspath()
            throws ParseException {
        List<Path> list = new ArrayList<>();
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
        List<Path> classpaths = getClasspath();
        URL[] urls = new URL[classpaths.size()];
        int i = 0;
        for (Path classpath : classpaths) {
            URL url = FileURL.toURL(classpath, null);
            urls[i++] = url;
        }
        return urls;
    }

}
