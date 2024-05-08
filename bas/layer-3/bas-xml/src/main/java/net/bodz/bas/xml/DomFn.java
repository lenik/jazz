package net.bodz.bas.xml;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public class DomFn {

    static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public static Document parseXml(String xml)
            throws ParseException {
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }

        StringReader reader = new StringReader(xml);
        InputSource source = new InputSource(reader);

        Document document;
        try {
            document = builder.parse(source);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        } catch (SAXException e) {
            throw new ParseException(e.getMessage(), e);
        }
        return document;
    }

}
