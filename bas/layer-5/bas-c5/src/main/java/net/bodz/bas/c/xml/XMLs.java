package net.bodz.bas.c.xml;

import java.io.*;
import java.net.URL;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import net.bodz.bas.c.java.beans.Jdk7XMLEncoder;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.EncodeException;
import net.bodz.bas.err.ExceptionBuffer;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.jvm.stack.Caller;

import com.googlecode.openbeans.Encoder;
import com.googlecode.openbeans.ExceptionListener;
import com.googlecode.openbeans.Expression;
import com.googlecode.openbeans.PersistenceDelegate;
import com.googlecode.openbeans.XMLDecoder;
import com.googlecode.openbeans.XMLEncoder;

public class XMLs {

    // SAX Parsers

    public static void parse(File file, Object... handlers)
            throws SAXException, IOException {
        assert file != null : "null file";
        URL url = file.toURI().toURL();
        parse(new InputSource(url.toString()), handlers);
    }

    public static void parse(Reader reader, Object... handlers)
            throws SAXException, IOException {
        assert reader != null : "null reader";
        parse(new InputSource(reader), handlers);
    }

    public static void parse(InputStream in, Object... handlers)
            throws SAXException, IOException {
        assert in != null : "null in";
        parse(new InputSource(in), handlers);
    }

    public static void parse(InputSource source, Object... handlers)
            throws SAXException, IOException {
        assert source != null : "null source";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new SAXException(e.getMessage(), e);
        }

        XMLReader xmlReader = saxParser.getXMLReader();
        try {
            xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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

    // XMLEncoder/XMLDecoder

    static Object readObject(XMLDecoder decoder, int caller) {
        Thread thread = Thread.currentThread();
        ClassLoader orig = thread.getContextClassLoader();
        ClassLoader loader = Caller.getCallerClassLoader(caller);
        if (orig == loader)
            return decoder.readObject();
        else
            try {
                thread.setContextClassLoader(loader);
                return decoder.readObject();
            } finally {
                thread.setContextClassLoader(orig);
            }
    }

    static void writeObject(XMLEncoder encoder, Object obj, int caller) {
        Thread thread = Thread.currentThread();
        ClassLoader orig = thread.getContextClassLoader();
        ClassLoader loader = Caller.getCallerClassLoader(caller + 1);
        if (orig == loader)
            encoder.writeObject(obj);
        else
            try {
                thread.setContextClassLoader(loader);
                encoder.writeObject(obj);
            } finally {
                thread.setContextClassLoader(orig);
            }
    }

    public static void encode(int caller, Object obj, OutputStream out, String encoding,
            ExceptionListener exceptionListener) {
        XMLEncoder encoder = Jdk7XMLEncoder.getInstance(out, encoding, true, 0);
        encoder.setPersistenceDelegate(URL.class, //
                new PersistenceDelegate() {
                    @Override
                    protected Expression instantiate(Object oldInstance, Encoder out) {
                        return new Expression(//
                                oldInstance, //
                                oldInstance.getClass(), //
                                "new", //
                                new Object[] { oldInstance.toString() });
                    }
                });
        encoder.setExceptionListener(exceptionListener);
        writeObject(encoder, obj, caller + 1);
        encoder.close();
    }

    public static void encode(int caller, Object obj, OutputStream out, ExceptionListener exceptionListener) {
        encode(caller + 1, obj, out, "utf-8", exceptionListener);
    }

    public static String encode(int caller, Object obj, ExceptionListener exceptionListener) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        encode(caller + 1, obj, buf, "utf-8", exceptionListener);
        String xml;
        try {
            xml = buf.toString("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
        return xml;
    }

    public static void encode(int caller, Object obj, OutputStream out)
            throws EncodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        encode(caller + 1, obj, out, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new EncodeException(errmesg);
    }

    public static String encode(int caller, Object obj)
            throws EncodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        String xml = encode(caller + 1, obj, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new EncodeException(errmesg);
        return xml;
    }

    public static <T> T decode(int caller, InputStream in, ExceptionListener exceptionListener) {
        XMLDecoder decoder = new XMLDecoder(in, exceptionListener);

        @SuppressWarnings("unchecked")
        T obj = (T) readObject(decoder, caller + 1);

        decoder.close();
        return obj;
    }

    public static <T> T decode(int caller, InputStream in)
            throws DecodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        T obj = decode(caller + 1, in, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new DecodeException(errmesg);
        return obj;
    }

    public static <T> T decode(int caller, String xml, ExceptionListener exceptionListener) {
        byte[] bytes;
        try {
            bytes = xml.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return decode(caller + 1, in, exceptionListener);
    }

    public static <T> T decode(int caller, String xml)
            throws DecodeException {
        ExceptionBuffer eb = new ExceptionBuffer();

        @SuppressWarnings("unchecked")
        T obj = (T) decode(caller + 1, xml, eb);

        String errmesg = eb.summary();
        if (errmesg != null)
            throw new DecodeException(errmesg);
        return obj;
    }

    // Using implicit callers:

    public static Object decode(InputStream in, ExceptionListener exceptionListener) {
        return decode(1, in, exceptionListener);
    }

    public static Object decode(InputStream in)
            throws DecodeException {
        return decode(1, in);
    }

    public static <T> T decode(String xml, ExceptionListener exceptionListener) {
        return decode(1, xml, exceptionListener);
    }

    @SuppressWarnings("unchecked")
    public static <T> T decode(String xml)
            throws DecodeException {
        return (T) decode(1, xml);
    }

    public static String encode(Object obj, ExceptionListener exceptionListener) {
        return encode(1, obj, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out, ExceptionListener exceptionListener) {
        encode(1, obj, out, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out, String encoding, ExceptionListener exceptionListener) {
        encode(1, obj, out, encoding, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out)
            throws EncodeException {
        encode(1, obj, out);
    }

    public static String encode(Object obj)
            throws EncodeException {
        return encode(1, obj);
    }

    // JAXB shortcuts

    public static String marshal(Object jaxbObject)
            throws DataBindingException {
        return marshal(jaxbObject, 1024);
    }

    public static String marshal(Object jaxbObject, int appxSize)
            throws DataBindingException {
        StringWriter buf = new StringWriter(appxSize);
        JAXB.marshal(jaxbObject, buf);
        return buf.toString();
    }

    public static <T> T unmarshal(String xml, Class<T> type) {
        if (xml == null)
            throw new NullPointerException("xml");
        StringReader reader = new StringReader(xml);
        return JAXB.unmarshal(reader, type);
    }

    public static <T> T unmarshal(byte[] xml, Class<T> type) {
        if (xml == null)
            throw new NullPointerException("xml");
        ByteArrayInputStream in = new ByteArrayInputStream(xml);
        return JAXB.unmarshal(in, type);
    }

}
