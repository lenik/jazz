package net.bodz.bas.fmt.xml;

import org.w3c.dom.Element;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;

public interface IObjectXmlLoader {

    void loadXmlToObject(Object obj, Element element)
            throws ParseException, LoaderException;

}
