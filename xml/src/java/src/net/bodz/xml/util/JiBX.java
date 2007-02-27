package net.bodz.xml.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

public class JiBX {

    public static Object parse(Class clazz, Reader xmlin) throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin);
        return obj;
    }

    public static Object parse(Class clazz, InputStream xmlin, String encoding)
            throws JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(xmlin, encoding);
        return obj;
    }

    public static Object parse(Class clazz, InputStream xmlin)
            throws JiBXException {
        return parse(clazz, xmlin, null);
    }

    public static Object parse(Class clazz, String xmlpath, String encoding)
            throws JiBXException, FileNotFoundException {
        IBindingFactory bfact = BindingDirectory.getFactory(clazz);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(new FileInputStream(xmlpath),
                encoding);
        return obj;
    }

    public static Object parse(Class clazz, String xmlpath)
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

    public static String rewrite(Object obj, String encoding)
            throws JiBXException {
        Writer buf = new StringWriter();
        rewrite(obj, buf, encoding);
        return buf.toString();
    }

    public static String rewrite(Object obj) throws JiBXException {
        return rewrite(obj, "utf-8");
    }

    public static ArrayList arrayList() throws JiBXException {
        return new ArrayList();
    }

    public static LinkedList linkedList() throws JiBXException {
        return new LinkedList();
    }

    public static Vector vector() throws JiBXException {
        return new Vector();
    }

}
