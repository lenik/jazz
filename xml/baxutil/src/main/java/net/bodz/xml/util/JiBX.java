package net.bodz.xml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

/**
 * @test {@link JiBXTest}
 */
public class JiBX {

    public static Object parse(Class<?> clazz, Reader xmlin) throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin);
        if (obj instanceof InputSourceTrace)
            ((InputSourceTrace) obj).setInputSource(xmlin);
        return obj;
    }

    public static Object parse(Class<?> clazz, InputStream xmlin, String encoding)
            throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin, encoding);
        if (obj instanceof InputSourceTrace)
            ((InputSourceTrace) obj).setInputSource(xmlin);
        return obj;
    }

    public static Object parse(Class<?> clazz, InputStream xmlin) throws JiBXException {
        return parse(clazz, xmlin, null);
    }

    public static Object parse(Class<?> clazz, String xmlpath, String encoding)
            throws JiBXException, FileNotFoundException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(new FileInputStream(xmlpath), encoding);
        if (obj instanceof InputSourceTrace)
            ((InputSourceTrace) obj).setInputSource(xmlpath);
        return obj;
    }

    public static Object parse(Class<?> clazz, String xmlpath) throws JiBXException,
            FileNotFoundException {
        return parse(clazz, xmlpath, null);
    }

    public static void rewrite(Object obj, Writer xmlout, String encoding) throws JiBXException {
        assert obj != null;
        IBindingFactory bfact = BindingDirectory.getFactory(obj.getClass());
        IMarshallingContext mctx = bfact.createMarshallingContext();
        mctx.marshalDocument(obj, encoding, null, xmlout);
    }

    public static void rewrite(Object obj, OutputStream xmlout, String encoding)
            throws JiBXException {
        assert obj != null;
        IBindingFactory bfact = BindingDirectory.getFactory(obj.getClass());
        IMarshallingContext mctx = bfact.createMarshallingContext();
        mctx.marshalDocument(obj, encoding, null, xmlout);
    }

    public static String rewrite(Object obj, String encoding) throws JiBXException {
        Writer buf = new StringWriter();
        rewrite(obj, buf, encoding);
        return buf.toString();
    }

    public static String rewrite(Object obj) throws JiBXException {
        return rewrite(obj, "utf-8");
    }

    public static String dirname(Object inputSource) {
        if (inputSource == null)
            return null;
        if (inputSource instanceof String)
            return new File((String) inputSource).getParent();
        if (inputSource instanceof File)
            return ((File) inputSource).getParent();
        if (inputSource instanceof URL) {
            String url = ((URL) inputSource).toString();
            int p = url.lastIndexOf('?');
            if (p >= 0)
                url = url.substring(0, p);
            p = url.lastIndexOf('/');
            if (p < 0)
                return null; // unexpected?
            return url.substring(0, p);
        }
        throw new UnsupportedOperationException("Can't fetch dirname of class "
                + inputSource.getClass());
    }

}
