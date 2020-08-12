package net.bodz.bas.t.factory;

import com.googlecode.openbeans.ExceptionListener;

import net.bodz.bas.c.xml.XMLs;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.DecodeException;

public class DecodeXMLFactory
        extends AbstractFactory<Object> {

    // private ClassLoader loader;
    String xml;
    ExceptionListener listener;

    public DecodeXMLFactory(String xml, ExceptionListener listener) {
        if (xml == null)
            throw new NullPointerException("xml");
        this.xml = xml;
        this.listener = listener;
    }

    @Override
    public Class<? extends Object> getType() {
        return Object.class;
    }

    @Override
    public Object _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        if (listener == null)
            try {
                return XMLs.decode(xml);
            } catch (DecodeException e) {
                throw new CreateException(e);
            }
        else
            return XMLs.decode(xml, listener);
    }

}
