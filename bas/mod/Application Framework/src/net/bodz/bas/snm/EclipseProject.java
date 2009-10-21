package net.bodz.bas.snm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.xml.XMLs;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EclipseProject {

    private final File base;

    public EclipseProject(File base) {
        assert base != null : "null base"; //$NON-NLS-1$
        this.base = base;
    }

    public static EclipseProject findFromCWD() {
        File base = SJProject.findProjectBase(new File(".")); //$NON-NLS-1$
        if (base == null)
            throw new RuntimeException(AppNLS.getString("EclipseProject.cantFindProject")); //$NON-NLS-1$
        return new EclipseProject(base);
    }

    public File getBase() {
        return base;
    }

    public List<File> getFileClasspath() throws ParseException {
        File classpathFile = new File(base, ".classpath"); //$NON-NLS-1$
        final List<File> classpaths = new ArrayList<File>();
        try {
            XMLs.parse(classpathFile, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String name,
                        Attributes attributes) throws SAXException {
                    if (!"classpathentry".equals(name)) //$NON-NLS-1$
                        return;
                    String kind = attributes.getValue("kind"); //$NON-NLS-1$
                    String path = attributes.getValue("path"); //$NON-NLS-1$
                    if ("output".equals(kind)) // default //$NON-NLS-1$
                        classpaths.add(new File(base, path));
                    if ("src".equals(kind)) {//$NON-NLS-1$
                        String output = attributes.getValue("output"); //$NON-NLS-1$
                        if (output != null)
                            classpaths.add(new File(base, output));
                    }
                    if ("con".equals(kind)) {
                        // if ("org.eclipse.pde.core.requiredPlugins".equals(path))
                        // JUST IGNORE CURRENTLY.
                    }
                    if ("var".equals(kind)) {
                        String varName = Strings.before(path, "/");
                        String expansion = Strings.after(path, "/");
                        // find the var...
                    }
                }
            });
        } catch (IOException e) {
            throw new ParseException(e);
        } catch (SAXException e) {
            throw new ParseException(e);
        }
        File manifestFile = new File(base, "META-INF/MANIFEST.MF");

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
        return classpaths;
    }

    /**
     * Convert classpaths to URL[].
     */
    public URL[] getURLClasspath() throws ParseException {
        List<File> classpaths = getFileClasspath();
        URL[] urls = new URL[classpaths.size()];
        int i = 0;
        for (File classpath : classpaths) {
            URL url = Files.getURL(classpath);
            urls[i++] = url;
        }
        return urls;
    }

    public EclipseWorkspace findWorkspace() {
        throw new NotImplementedException();
    }

}
