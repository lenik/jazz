package net.bodz.bas.xml;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.DecodeException;
import net.bodz.bas.lang.err.EncodeException;
import net.bodz.bas.lang.err.UnexpectedException;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @test {@link XMLsTest}
 */
public class XMLs {

    // SAX Parsers

    public static void parse(File file, Object... handlers) throws SAXException, IOException {
        assert file != null : "null file"; //$NON-NLS-1$
        URL url = Files.getURL(file);
        parse(new InputSource(url.toString()), handlers);
    }

    public static void parse(Reader reader, Object... handlers) throws SAXException, IOException {
        assert reader != null : "null reader"; //$NON-NLS-1$
        parse(new InputSource(reader), handlers);
    }

    public static void parse(InputStream in, Object... handlers) throws SAXException, IOException {
        assert in != null : "null in"; //$NON-NLS-1$
        parse(new InputSource(in), handlers);
    }

    public static void parse(InputSource source, Object... handlers) throws SAXException,
            IOException {
        assert source != null : "null source"; //$NON-NLS-1$
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        for (Object handler : handlers) {
            if (handler == null)
                throw new NullPointerException("null handler"); //$NON-NLS-1$
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
        XMLEncoder enc = new XMLEncoder(out, encoding, true, 0);
        enc.setExceptionListener(exceptionListener);
        writeObject(enc, obj, caller + 1);
        enc.close();
    }

    public static void encode(int caller, Object obj, OutputStream out,
            ExceptionListener exceptionListener) {
        encode(caller + 1, obj, out, "utf-8", exceptionListener); //$NON-NLS-1$
    }

    public static String encode(int caller, Object obj, ExceptionListener exceptionListener) {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        encode(caller + 1, obj, buf, "utf-8", exceptionListener); //$NON-NLS-1$
        String xml;
        try {
            xml = buf.toString("utf-8"); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
        return xml;
    }

    public static void encode(int caller, Object obj, OutputStream out) throws EncodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        encode(caller + 1, obj, out, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new EncodeException(errmesg);
    }

    public static String encode(int caller, Object obj) throws EncodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        String xml = encode(caller + 1, obj, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new EncodeException(errmesg);
        return xml;
    }

    public static Object decode(int caller, InputStream in, ExceptionListener exceptionListener) {
        XMLDecoder decoder = new XMLDecoder(in, exceptionListener);
        Object obj = readObject(decoder, caller + 1);
        decoder.close();
        return obj;
    }

    public static Object decode(int caller, InputStream in) throws DecodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        Object obj = decode(caller + 1, in, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new DecodeException(errmesg);
        return obj;
    }

    public static Object decode(int caller, String xml, ExceptionListener exceptionListener) {
        byte[] bytes;
        try {
            bytes = xml.getBytes("utf-8"); //$NON-NLS-1$
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return decode(caller + 1, in, exceptionListener);
    }

    public static Object decode(int caller, String xml) throws DecodeException {
        ExceptionBuffer eb = new ExceptionBuffer();
        Object obj = decode(caller + 1, xml, eb);
        String errmesg = eb.summary();
        if (errmesg != null)
            throw new DecodeException(errmesg);
        return obj;
    }

    // Using implicit callers:

    public static Object decode(InputStream in, ExceptionListener exceptionListener) {
        return decode(1, in, exceptionListener);
    }

    public static Object decode(InputStream in) throws DecodeException {
        return decode(1, in);
    }

    public static Object decode(String xml, ExceptionListener exceptionListener) {
        return decode(1, xml, exceptionListener);
    }

    public static Object decode(String xml) throws DecodeException {
        return decode(1, xml);
    }

    public static String encode(Object obj, ExceptionListener exceptionListener) {
        return encode(1, obj, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out, ExceptionListener exceptionListener) {
        encode(1, obj, out, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out, String encoding,
            ExceptionListener exceptionListener) {
        encode(1, obj, out, encoding, exceptionListener);
    }

    public static void encode(Object obj, OutputStream out) throws EncodeException {
        encode(1, obj, out);
    }

    public static String encode(Object obj) throws EncodeException {
        return encode(1, obj);
    }

}
