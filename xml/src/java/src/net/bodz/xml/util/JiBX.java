package net.bodz.xml.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class JiBX {

    @SuppressWarnings("unchecked")
    public static <T> T parse(Class<T> clazz, Reader xmlin)
            throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin);
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(Class<T> clazz, InputStream xmlin, String encoding)
            throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin, encoding);
        return (T) obj;
    }

    public static <T> T parse(Class<T> clazz, InputStream xmlin)
            throws JiBXException {
        return parse(clazz, xmlin, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(Class<T> clazz, String xmlpath, String encoding)
            throws JiBXException, FileNotFoundException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(new FileInputStream(xmlpath),
                encoding);
        return (T) obj;
    }

    public static <T> T parse(Class<T> clazz, String xmlpath)
            throws JiBXException, FileNotFoundException {
        return parse(clazz, xmlpath, null);
    }

    public static void rewrite(Object obj, Writer xmlout, String encoding)
            throws JiBXException {
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

}
