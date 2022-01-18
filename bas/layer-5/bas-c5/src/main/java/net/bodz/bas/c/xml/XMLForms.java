package net.bodz.bas.c.xml;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.typer.Typers;

public class XMLForms {

    public static Object parse(Class<?> type, IStreamInputSource source) {
        IXMLFormat<?> xmlFormat = Typers.getTyper(type, IXMLFormat.class);
        if (xmlFormat == null)
            return null;
        return null;
    }

}
