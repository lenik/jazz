package net.bodz.bas.snm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.xml.XMLs;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EclipseProject {

    private File base;

    public EclipseProject(File base) {
        assert base != null : "null base"; //$NON-NLS-1$
        this.base = base;
    }

    public static EclipseProject findFromCWD() {
        File base = SJProject.findProjectBase(new File(".")); //$NON-NLS-1$
        if (base == null)
            throw new RuntimeException(AppNLS
                    .getString("EclipseProject.cantFindProject")); //$NON-NLS-1$
        return new EclipseProject(base);
    }

    public File getBase() {
        return base;
    }

    public void setBase(File base) {
        this.base = base;
    }

    public URL[] getClasspaths() throws ParseException {
        File cpFile = new File(base, ".classpath"); //$NON-NLS-1$
        final String[] _output = new String[1];
        final List<String> outputs = new ArrayList<String>();
        try {
            XMLs.parse(cpFile, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName,
                        String name, Attributes attributes) throws SAXException {
                    if (!"classpathentry".equals(name)) //$NON-NLS-1$
                        return;
                    String kind = attributes.getValue("kind"); //$NON-NLS-1$
                    String path = attributes.getValue("path"); //$NON-NLS-1$
                    if ("output".equals(kind)) { // default //$NON-NLS-1$
                        _output[0] = path;
                    }
                    if (!"src".equals(kind)) //$NON-NLS-1$
                        return;
                    String output = attributes.getValue("output"); //$NON-NLS-1$
                    if (output != null)
                        outputs.add(output);
                }
            });
        } catch (IOException e) {
            throw new ParseException(e);
        } catch (SAXException e) {
            throw new ParseException(e);
        }
        String output = _output[0];
        int n = outputs.size();
        if (output != null)
            n++;
        URL[] urls = new URL[n];
        int i = 0;
        for (String s : outputs) {
            URL url = Files.getURL(new File(base, s));
            urls[i++] = url;
        }
        if (output != null)
            urls[i] = Files.getURL(new File(base, output));
        return urls;
    }

    public EclipseWorkspace findWorkspace() {
        return null;
    }

}
