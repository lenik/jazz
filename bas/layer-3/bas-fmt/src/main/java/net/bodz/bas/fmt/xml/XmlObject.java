package net.bodz.bas.fmt.xml;

import java.io.StringWriter;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.xml.xq.IElement;

public abstract class XmlObject
        implements
            IXmlSerializable,
            IXmlOverrides {

    private transient IObjectXmlLoader _loader;

    private IObjectXmlLoader _loader() {
        if (_loader == null)
            synchronized (this) {
                if (_loader == null)
                    _loader = XmlFn.getDefaultLoader(this);
            }
        return _loader;
    }

    /** ⇱ Implementation Of {@link IXmlSerializable}. */
    /* _____________________________ */static section.iface __RST_SERIALIZABLE__;

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        XmlFn.dump(this, out);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        _loader().loadXmlToObject(this, element);
    }

    /** ⇱ Implementation Of {@link IXmlOverrides}. */
    /* _____________________________ */static section.iface __OVERRIDES__;

    @Override
    public boolean writeSpecialXmlEntry(IXmlOutput out, String field)
            throws XMLStreamException {
        return false;
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

    @Override
    public String toString() {
        StringWriter buf = new StringWriter(4096);
        try {
            IXmlOutput out = XmlOutputImpl.from(buf);
            writeObject(out);
        } catch (FormatException e) {
            throw new UnexpectedException(e.getMessage(), e);
        } catch (XMLStreamException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        String xml = buf.toString();
        return xml;
    }

}
