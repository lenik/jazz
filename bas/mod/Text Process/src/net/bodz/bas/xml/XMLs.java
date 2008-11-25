package net.bodz.bas.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import net.bodz.bas.io.Files;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLs {

    public static void parseXML(File file, Object... handlers)
            throws SAXException, IOException {
        assert file != null : "null file";
        URL url = Files.getURL(file);
        parseXML(new InputSource(url.toString()), handlers);
    }

    public static void parseXML(Reader reader, Object... handlers)
            throws SAXException, IOException {
        assert reader != null : "null reader";
        parseXML(new InputSource(reader), handlers);
    }

    public static void parseXML(InputStream in, Object... handlers)
            throws SAXException, IOException {
        assert in != null : "null in";
        parseXML(new InputSource(in), handlers);
    }

    public static void parseXML(InputSource source, Object... handlers)
            throws SAXException, IOException {
        assert source != null : "null source";
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        for (Object handler : handlers) {
            if (handler == null)
                throw new NullPointerException("null handler");
            if (handler instanceof DefaultHandler) {
                DefaultHandler dh = (DefaultHandler) handler;
                xmlReader.setContentHandler(dh);
                xmlReader.setDTDHandler(dh);
                xmlReader.setErrorHandler(dh);
                xmlReader.setEntityResolver(dh);
            } else {
                if (handler instanceof ContentHandler)
                    xmlReader.setContentHandler((ContentHandler) handler);
                if (handler instanceof DTDHandler)
                    xmlReader.setDTDHandler((DTDHandler) handler);
                if (handler instanceof ErrorHandler)
                    xmlReader.setErrorHandler((ErrorHandler) handler);
                if (handler instanceof EntityResolver)
                    xmlReader.setEntityResolver((EntityResolver) handler);
            }
        }
        xmlReader.parse(source);
    }

}
