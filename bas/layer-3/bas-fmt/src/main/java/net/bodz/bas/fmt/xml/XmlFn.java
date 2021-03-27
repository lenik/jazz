package net.bodz.bas.fmt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.obj.BeanXmlDumper;
import net.bodz.bas.fmt.xml.obj.BeanXmlLoader;
import net.bodz.bas.fmt.xml.obj.ReflectXmlDumper;
import net.bodz.bas.fmt.xml.obj.ReflectXmlLoader;
import net.bodz.bas.fmt.xml.obj.XmlSource;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class XmlFn {

    public static IObjectXmlLoader getDefaultLoader(IXmlSerializable obj) {
        XmlSource aXmlSource = obj.getClass().getAnnotation(XmlSource.class);
        if (aXmlSource != null)
            if (aXmlSource.bean() == true)
                return new BeanXmlLoader(obj);
        return new ReflectXmlLoader(obj);
    }

    public static void defaultDump(IXmlSerializable obj, IXmlOutput out)
            throws XMLStreamException {
        XmlSource aXmlSource = obj.getClass().getAnnotation(XmlSource.class);
        if (aXmlSource != null)
            if (aXmlSource.bean() == true)
                new BeanXmlDumper(out).dump(obj);
        new ReflectXmlDumper(out).dump(obj);
    }

    public static String toString(IXmlSerializable obj)
            throws XMLStreamException {
        StringWriter buf = new StringWriter(4096);
        IXmlOutput out = XmlOutputImpl.from(buf);
        obj.writeObject(out);
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

    public static void saveToXml(IXmlSerializable obj, File file)
            throws IOException, XMLStreamException {
        saveToXml(obj, file, "utf-8");
    }

    public static void saveToXml(IXmlSerializable obj, File file, String encoding)
            throws IOException, XMLStreamException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            IXmlOutput out = XmlOutputImpl.from(fos, encoding);
            obj.writeObject(out);
        } finally {
            fos.close();
        }
    }

    public static void loadFromXml(IXmlSerializable ctx, File file)
            throws IOException, LoaderException, ParseException {
        if (!file.exists())
            return;
        XmlLoader.load(ctx, file);
    }

}
