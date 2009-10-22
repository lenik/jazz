package net.bodz.xml.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXSourceWithModifiedEntities extends SAXSource implements EntityResolver {

    public SAXSourceWithModifiedEntities(InputSource inputSource)
            throws ParserConfigurationException, SAXException {
        super(inputSource);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLReader reader = saxParser.getXMLReader();
        reader.setEntityResolver(this);
        setXMLReader(reader);
    }

    public SAXSourceWithModifiedEntities(InputStream byteStream)
            throws ParserConfigurationException, SAXException {
        this(new InputSource(byteStream));
    }

    public SAXSourceWithModifiedEntities(Reader characterStream)
            throws ParserConfigurationException, SAXException {
        this(new InputSource(characterStream));
    }

    public SAXSourceWithModifiedEntities(String systemId) throws ParserConfigurationException,
            SAXException {
        this(new InputSource(systemId));
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
            IOException {
        String mesg = "<!-- dropped entity: " + publicId + " -->";
        return new InputSource(new StringReader(mesg));
    }

}
