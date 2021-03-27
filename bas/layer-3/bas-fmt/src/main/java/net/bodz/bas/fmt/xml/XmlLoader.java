package net.bodz.bas.fmt.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.IStreamInputSource;

public class XmlLoader {

    static XMLInputFactory inputFactory = XMLInputFactory.newFactory();
    static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

    public static void load(IXmlSerializable obj, InputSource in)
            throws LoaderException, ParseException {
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(in);
            Element root = doc.getDocumentElement();
            obj.readObject(root);
        } catch (LoaderException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        } catch (Exception e) {
            throw new LoaderException(e.getMessage(), e);
        }
    }

    public static void load(IXmlSerializable obj, IStreamInputSource resource)
            throws LoaderException, ParseException {
        InputStream in = null;
        // resource.getPath()
        try {
            in = resource.newInputStream();
            load(obj, new InputSource(in));
        } catch (IOException e) {
            throw new LoaderException(e.getMessage(), e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    throw new LoaderException(e.getMessage(), e);
                }
        }
    }

    public static void load(IXmlSerializable obj, File file)
            throws LoaderException, ParseException {
        if (!file.exists())
            return;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            load(obj, new InputSource(in));
        } catch (IOException e) {
            throw new LoaderException(e.getMessage(), e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new LoaderException(e.getMessage(), e);
            }
        }
    }

}
