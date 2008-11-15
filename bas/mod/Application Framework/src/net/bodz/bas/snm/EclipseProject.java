package net.bodz.bas.snm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.xml.XMLs;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EclipseProject {

    private File base;

    public EclipseProject(File base) {
        assert base != null : "null base";
        this.base = base;
    }

    public static EclipseProject findFromCWD() {
        File base = SJProject.findProjectBase(new File("."));
        if (base == null)
            throw new RuntimeException("can't find the project");
        return new EclipseProject(base);
    }

    public URL[] getClasspaths() throws ParseException {
        File cpFile = new File(base, ".classpath");
        final String[] _output = new String[1];
        final List<String> outputs = new ArrayList<String>();
        try {
            XMLs.parseXML(cpFile, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName,
                        String name, Attributes attributes) throws SAXException {
                    if (!"classpathentry".equals(name))
                        return;
                    String kind = attributes.getValue("kind");
                    String path = attributes.getValue("path");
                    if ("output".equals(kind)) { // default
                        _output[0] = path;
                    }
                    if (!"src".equals(kind))
                        return;
                    String output = attributes.getValue("output");
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

}
