package net.bodz.bas.fmt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.xml.obj.BeanXmlDumper;
import net.bodz.bas.fmt.xml.obj.BeanXmlLoader;
import net.bodz.bas.fmt.xml.obj.ReflectXmlDumper;
import net.bodz.bas.fmt.xml.obj.ReflectXmlLoader;
import net.bodz.bas.fmt.xml.obj.XmlSource;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class XmlFn
        extends XmlLoader {

    public static IObjectXmlLoader getDefaultLoader(Object obj) {
        XmlSource aXmlSource = obj.getClass().getAnnotation(XmlSource.class);
        if (aXmlSource != null)
            if (aXmlSource.bean() == true)
                return new BeanXmlLoader(obj);
        return new ReflectXmlLoader(obj);
    }

    public static void dump(Object obj, IXmlOutput out)
            throws XMLStreamException, FormatException {
        XmlSource aXmlSource = obj.getClass().getAnnotation(XmlSource.class);
        if (aXmlSource != null)
            if (aXmlSource.bean() == true)
                new BeanXmlDumper(out).dump(obj);
        new ReflectXmlDumper(out).dump(obj);
    }

    public static void dump(Object obj, Writer out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(Object obj, Appendable out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(Object obj, OutputStream out, String encoding)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out, encoding));
    }

    public static void dump(IXmlSerializable obj, IXmlOutput out)
            throws XMLStreamException, FormatException {
        obj.writeObject(out);
    }

    public static void dump(IXmlSerializable obj, Writer out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(IXmlSerializable obj, Appendable out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(IXmlSerializable obj, OutputStream out, String encoding)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out, encoding));
    }

    public static String toString(IXmlSerializable obj)
            throws XMLStreamException {
        StringWriter buf = new StringWriter(4096);
        IXmlOutput out = XmlOutputImpl.from(buf);
        try {
            obj.writeObject(out);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        String rst = buf.toString();
        return rst;
    }

    public static String toString(IXmlSerializable obj, String fallbackFormat) {
        try {
            return toString(obj);
        } catch (XMLStreamException e) {
            return String.format(fallbackFormat, e.getMessage());
        }
    }

    public static void save(IXmlSerializable obj, File file)
            throws IOException, XMLStreamException, FormatException {
        save(obj, file, "utf-8");
    }

    public static void save(IXmlSerializable obj, File file, String encoding)
            throws IOException, XMLStreamException, FormatException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            IXmlOutput out = XmlOutputImpl.from(fos, encoding);
            obj.writeObject(out);
        } finally {
            fos.close();
        }
    }

}
