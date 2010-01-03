package net.bodz.bas.snm;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.commons.exceptions.UnexpectedException;
import net.bodz.bas.io.Files;
import net.bodz.bas.xml.XMLs;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @test {@link BuildPathTest}
 */
public class BuildPath {

    public static class SourceFolder {
        public File path;
        public File output;
        public String[] includes;
        public String[] excludes;
        public File nativeLibraryLocation;

        @Override
        public String toString() {
            return String.valueOf(path);
        }
    }

    List<SourceFolder> sourceFolders;
    File defaultOutput;

    List<EclipseProject> requiredProjects;

    public static class Library {
        public File path;
        public File sourceAttachment;
        public File javadocLocation;
        public File nativeLibraryLocation;
    }

    List<Library> libraries;

    String[] trySrcSuffixes;

    public BuildPath() {
        this.sourceFolders = new ArrayList<SourceFolder>();
        this.requiredProjects = new ArrayList<EclipseProject>();
        this.libraries = new ArrayList<Library>();
    }

    public BuildPath(Class<?> virtualProjectForClass) {
        this();
        String selfResourceName = "/" + virtualProjectForClass.getName().replace('.', '/') + ".class";
        URL selfRes = virtualProjectForClass.getResource(selfResourceName);
        if (selfRes == null)
            throw new UnexpectedException("Self resource error, maybe class extension isn't '.class'? ");
        File defaultClasspath;
        try {
            defaultClasspath = Files.getFile(selfRes, selfResourceName);
            Library defaultLibrary = new Library();
            defaultLibrary.path = defaultClasspath;
            addLibrary(defaultLibrary);
        } catch (MalformedURLException e) {
            ; // warn: non-local class is not supported
        }
    }

    public BuildPath(final File baseDir, File classpathFile, File manifestFile) throws ParseException {
        this();
        if (classpathFile != null && classpathFile.exists()) {
            try {
                XMLs.parse(classpathFile, new DefaultHandler() {
                    @Override
                    public void startElement(String uri, String localName, String name, Attributes attributes)
                            throws SAXException {
                        if (!"classpathentry".equals(name)) //$NON-NLS-1$
                            return;
                        String kind = attributes.getValue("kind"); //$NON-NLS-1$
                        String path = attributes.getValue("path"); //$NON-NLS-1$
                        if ("output".equals(kind)) // default //$NON-NLS-1$
                            defaultOutput = Files.canoniOf(baseDir, path);
                        if ("src".equals(kind)) {//$NON-NLS-1$
                            String output = attributes.getValue("output"); //$NON-NLS-1$
                            String nativelib = attributes.getValue("nativelib");
                            SourceFolder source = new SourceFolder();
                            source.path = Files.canoniOf(baseDir, path);
                            if (output != null)
                                source.output = Files.canoniOf(baseDir, output);
                            if (nativelib != null)
                                source.nativeLibraryLocation = Files.canoniOf(baseDir, nativelib);
                            sourceFolders.add(source);
                        }
                        if ("lib".equals(kind)) {
                            String sourcepath = attributes.getValue("sourcepath");
                            String javadoc = attributes.getValue("javadoc");
                            Library library = new Library();
                            library.path = Files.canoniOf(baseDir, path);
                            if (sourcepath != null)
                                library.sourceAttachment = Files.canoniOf(baseDir, sourcepath);
                            if (javadoc != null)
                                library.javadocLocation = Files.canoniOf(baseDir, javadoc);
                            libraries.add(library);
                        }
                        if ("var".equals(kind)) {
                            // String varName = Strings.before(path, "/");
                            // String expansion = Strings.after(path, "/");
                            // find the var...
                        }
                        if ("con".equals(kind)) {
                            // if ("org.eclipse.pde.core.requiredPlugins".equals(path))
                            // JUST IGNORE CURRENTLY.
                        }
                    }
                });
            } catch (IOException e) {
                throw new ParseException(e);
            } catch (SAXException e) {
                throw new ParseException(e);
            }
        }
        if (manifestFile != null && manifestFile.exists()) {
            try {
                Manifest manifest = new Manifest(Files.getInputStream(manifestFile));
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
            } catch (IOException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }
    }

    public List<SourceFolder> getSourceFolders() {
        return Collections.unmodifiableList(sourceFolders);
    }

    public void addSourceFolder(SourceFolder sourceFolder) {
        sourceFolders.add(sourceFolder);
    }

    public File getDefaultOutput() {
        return defaultOutput;
    }

    public List<EclipseProject> getRequiredProjects() {
        return Collections.unmodifiableList(requiredProjects);
    }

    public void addRequiredProject(EclipseProject project) {
        requiredProjects.add(project);
    }

    public List<Library> getLibraries() {
        return Collections.unmodifiableList(libraries);
    }

    public void addLibrary(Library library) {
        this.libraries.add(library);
    }

    public String[] getSrcSuffixes() {
        return trySrcSuffixes;
    }

    public void setSrcSuffixes(String... srcSuffixes) {
        this.trySrcSuffixes = srcSuffixes;
    }

    public List<File> getClasspath() throws ParseException {
        List<File> list = new ArrayList<File>();
        if (defaultOutput != null)
            list.add(defaultOutput);
        for (SourceFolder source : sourceFolders)
            if (source.output != null)
                list.add(source.output);
        for (Library l : libraries)
            list.add(l.path);
        return list;
    }

    static boolean isUnder(File childCanonified, File parentCanonified) {
        if (childCanonified == null)
            throw new NullPointerException("childCanonified");
        if (parentCanonified == null)
            throw new NullPointerException("parentCanonified");
        if (parentCanonified.isDirectory()) {
            if (childCanonified.equals(parentCanonified))
                return true;
            String childPath = childCanonified.getPath();
            String parentPath = parentCanonified.getPath();
            return childPath.startsWith(parentPath + File.separator);
        }
        return false;
    }

    static boolean isUnder(String childName, File parent) {
        if (childName == null)
            throw new NullPointerException("childName");
        if (parent == null)
            throw new NullPointerException("parent");
        String _childName;
        if (childName.startsWith("/")) {
            _childName = childName;
            childName = childName.substring(1);
        } else {
            _childName = "/" + childName;
        }
        if (parent.isDirectory()) {
            return new File(parent, childName).exists();
        } else if (parent.isFile()) {
            ZipFile zip = null;
            try {
                zip = new ZipFile(parent);
                // zip entry maybe(or not) starts-with /.
                ZipEntry entry = zip.getEntry(childName);
                if (entry != null)
                    return true;
                /* TRY absolute name */{
                    entry = zip.getEntry(_childName);
                    if (entry != null)
                        return true;
                }
            } catch (ZipException e) {
                return false;
            } catch (IOException e) {
                return false;
            } finally {
                if (zip != null)
                    try {
                        zip.close();
                    } catch (IOException e) {
                        // ignoe.
                    }
            }
        }
        return false;
    }

    public SourceFolder whichSourceFolder(File sourceFile) {
        sourceFile = Files.canoniOf(sourceFile);
        for (SourceFolder s : sourceFolders) {
            if (isUnder(sourceFile, s.path))
                return s;
        }
        return null;
    }

    public class ClassContainer {
        File sourcePath;
        File classPath;
        URL sourceURL;
        URL classURL;

        public ClassContainer(SourceFolder sourceFolder) {
            this.sourcePath = sourceFolder.path;
            this.classPath = sourceFolder.output == null ? defaultOutput : sourceFolder.output;
            _copyurl();
        }

        public ClassContainer(Library library) {
            this(library.path, library.sourceAttachment);
        }

        public ClassContainer(File classPath, File sourcePath) {
            this.classPath = classPath;
            if (sourcePath == null && trySrcSuffixes != null) {
                File parentDir = classPath.getParentFile();
                String classPathFileName = classPath.getName();
                String classPathName = Files.getName(classPathFileName);
                String classPathExtension = Files.getExtension(classPathFileName);
                for (String trySrcSuffix : trySrcSuffixes) {
                    String srcName = classPathName + trySrcSuffix;
                    File srcPath = new File(parentDir, srcName + classPathExtension);
                    if (srcPath.exists()) {
                        sourcePath = srcPath;
                        break;
                    }
                }
            }
            this.sourcePath = sourcePath;
            _copyurl();
        }

        void _copyurl() {
            if (sourcePath != null)
                this.sourceURL = f2u(sourcePath, null);
            if (classPath != null)
                this.classURL = f2u(classPath, null);
        }

        public File getSourcePath() {
            return sourcePath;
        }

        public File getClassPath() {
            return classPath;
        }

        public URL getSourceURL() {
            return sourceURL;
        }

        public URL getClassURL() {
            return classURL;
        }

        @Override
        public String toString() {
            StringBuffer buf = new StringBuffer();
            if (classPath != null)
                buf.append("class-path: " + classPath);
            else if (classURL != null)
                buf.append("class-url: " + classURL);
            buf.append(", ");
            if (sourcePath != null)
                buf.append("source-path: " + sourcePath);
            else if (sourceURL != null)
                buf.append("source-url: " + sourceURL);
            if (buf.length() == 0)
                return "(n/a)";
            return buf.toString();
        }

    }

    public ClassContainer whichClassContainer(Class<?> clazz) {
        return whichClassContainer(clazz.getName());
    }

    public ClassContainer whichClassContainer(String typeName) {
        String classFileName = typeName.replace('.', '/') + ".class";
        String javaFileName = typeName.replace('.', '/') + ".java";
        if (defaultOutput != null && isUnder(classFileName, defaultOutput)) {
            SourceFolder sourceFolder = null;
            for (SourceFolder sf : sourceFolders)
                if (isUnder(javaFileName, sf.path)) {
                    sourceFolder = sf;
                    break;
                }
            if (sourceFolder != null)
                return new ClassContainer(sourceFolder);
            else
                // none of source directories contains the source, try heuristic
                return new ClassContainer(defaultOutput, null);
        }
        for (SourceFolder sf : sourceFolders)
            if (isUnder(classFileName, sf.output))
                return new ClassContainer(sf);
        for (Library l : libraries)
            if (isUnder(classFileName, l.path))
                return new ClassContainer(l);
        return null;
    }

    public static class SourceResource {
        ClassContainer container;
        File file;
        URL url;

        public SourceResource(ClassContainer container, File file) {
            this.container = container;
            this.file = file;
            this.url = f2u(file, null);
        }

        public SourceResource(ClassContainer container, URL url) {
            this.container = container;
            this.url = url;
            this.file = u2f(url, null);
        }

        public ClassContainer getContainer() {
            return container;
        }

        public File getFile() {
            return file;
        }

        public URL getURL() {
            return url;
        }

        @Override
        public String toString() {
            if (file != null)
                return "file: " + file;
            else if (url != null)
                return "url: " + url;
            return "(n/a)";
        }
    }

    /**
     * @return resource name without <code>.class</code> extension.
     */
    static String getJavaName(Class<?> clazz) {
        String name = clazz.getName();
        int dollar = name.indexOf('$');
        if (dollar != -1)
            name = name.substring(0, dollar);
        String javaName = name.replace('.', '/') + ".java";
        return javaName;
    }

    public SourceResource findSourceResource(Class<?> clazz) {
        String resourceName = getJavaName(clazz);
        ClassContainer cc = whichClassContainer(clazz);
        if (cc == null)
            return null;
        // SourceResource sr = new SourceResource();
        File sourcePath = cc.getSourcePath();
        URL sourceURL = cc.getSourceURL();
        if (sourcePath == null) {
            if (sourceURL == null)
                return null;
            if ("file".equals(sourceURL.getProtocol()))
                sourcePath = u2f(sourceURL, null);
        }
        if (sourcePath != null & sourcePath.isFile()) {
            sourceURL = f2u(sourcePath, null);
            sourcePath = null;
        }
        if (sourcePath != null) {
            assert sourcePath.isDirectory();
            File javaFile = new File(sourcePath, resourceName);
            if (!javaFile.exists())
                throw new RuntimeException("java source file is missing: " + javaFile);
            return new SourceResource(cc, javaFile);
        }
        if (sourceURL != null) {
            if ("jar".equals(sourceURL.getProtocol())) {
                String javaURL = "jar:" + sourceURL + "!/" + resourceName;
                try {
                    return new SourceResource(cc, new URL(javaURL));
                } catch (MalformedURLException e) {
                    return null;
                }
            }
        }
        return null;
    }

    static URL f2u(File file, URL fallback) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return fallback;
        }
    }

    static File u2f(URL url, File fallback) {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return fallback;
        }
    }

    public static URL getSrcURL(Class<?> clazz) {
        SourceResource sourceResource = new BuildPath(clazz).findSourceResource(clazz);
        if (sourceResource != null) {
            return sourceResource.getURL();
        }
        return null;
    }

}
