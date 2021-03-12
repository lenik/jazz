package net.bodz.bas.c.org.eclipse;

import java.io.File;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import net.bodz.bas.c.java.io.FilePath;

public class ClasspathFileContentHandler
        extends DefaultHandler {

    JavaProject project;
    File baseDir;

    public ClasspathFileContentHandler(JavaProject project) {
        if (project == null)
            throw new NullPointerException("project");
        this.project = project;
        this.baseDir = project.baseDir;
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes)
            throws SAXException {
        if (!"classpathentry".equals(name))
            return;

        String kind = attributes.getValue("kind");
        String path = attributes.getValue("path");

        if ("output".equals(kind)) {
            project.defaultOutput = FilePath.canoniOf(baseDir, path);
        }

        else if ("src".equals(kind)) {
            String output = attributes.getValue("output");
            String nativelib = attributes.getValue("nativelib");

            JavaProjectSourceFolder sourceFolder = new JavaProjectSourceFolder();
            sourceFolder.path = FilePath.canoniOf(baseDir, path);
            if (output != null)
                sourceFolder.output = FilePath.canoniOf(baseDir, output);
            if (nativelib != null)
                sourceFolder.nativeLibraryLocation = FilePath.canoniOf(baseDir, nativelib);

            project.addSourceFolder(sourceFolder);
        }

        else if ("lib".equals(kind)) {
            String sourcepath = attributes.getValue("sourcepath");
            String javadoc = attributes.getValue("javadoc");

            JavaProjectLibraryRef library = new JavaProjectLibraryRef();
            library.path = FilePath.canoniOf(baseDir, path);
            if (sourcepath != null)
                library.sourceAttachment = FilePath.canoniOf(baseDir, sourcepath);
            if (javadoc != null)
                library.javadocLocation = FilePath.canoniOf(baseDir, javadoc);

            project.addLibraryRef(library);
        }

        else if ("var".equals(kind)) {
            // String varName = Strings.before(path, "/");
            // String expansion = Strings.after(path, "/");
            // find the var...
        }

        else if ("con".equals(kind)) {
            // if ("org.eclipse.pde.core.requiredPlugins".equals(path))
            // JUST IGNORE CURRENTLY.
        }
    }

}
