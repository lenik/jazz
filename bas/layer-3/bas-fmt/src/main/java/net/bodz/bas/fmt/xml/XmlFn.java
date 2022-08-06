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
        out.flush();
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

    public static void dump(IXmlForm obj, IXmlOutput out)
            throws XMLStreamException, FormatException {
        obj.writeObject(out);
        out.flush();
    }

    public static void dump(IXmlForm obj, Writer out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(IXmlForm obj, Appendable out)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out));
    }

    public static void dump(IXmlForm obj, OutputStream out, String encoding)
            throws XMLStreamException, FormatException {
        dump(obj, XmlOutputImpl.from(out, encoding));
    }

    public static String toString(IXmlForm obj)
            throws XMLStreamException {
        StringWriter buf = new StringWriter(4096);
        IXmlOutput out = XmlOutputImpl.from(buf);
        try {
            obj.writeObjectBoxed(out);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        out.flush();
        String rst = buf.toString();
        return rst;
    }

    public static String toString(IXmlForm obj, String fallbackFormat) {
        try {
            return toString(obj);
        } catch (XMLStreamException e) {
            return String.format(fallbackFormat, e.getMessage());
        }
    }

    public static void save(IXmlForm obj, File file)
            throws IOException, XMLStreamException, FormatException {
        save(obj, file, "utf-8");
    }

    public static void save(IXmlForm obj, File file, String encoding)
            throws IOException, XMLStreamException, FormatException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            IXmlOutput out = XmlOutputImpl.from(fos, encoding);
            obj.writeObjectBoxed(out);
            out.flush();
        } finally {
            fos.close();
        }
    }

}
