package net.bodz.bas.t.factory;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.bodz.bas.c.xml.XMLs;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.DecodeException;

public class DecodeXMLFileFactory
        extends AbstractFactory<Object> {

    // private ClassLoader loader;
    File xmlFile;
    ExceptionListener listener;

    public DecodeXMLFileFactory(File xmlFile, ExceptionListener listener) {
        if (xmlFile == null)
            throw new NullPointerException("xmlFile");
        this.xmlFile = xmlFile;
        this.listener = listener;
    }

    @Override
    public Class<? extends Object> getType() {
        return Object.class;
    }

    @Override
    public Object _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(xmlFile);
            if (listener == null)
                return XMLs.decode(in);
            else
                return XMLs.decode(in, listener);
        } catch (DecodeException e) {
            throw new CreateException(e);
        } catch (IOException e) {
            throw new CreateException(e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    throw new CreateException(e);
                }
        }
    }

}
