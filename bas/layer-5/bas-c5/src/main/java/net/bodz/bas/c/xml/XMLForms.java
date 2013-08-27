package net.bodz.bas.c.xml;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.typer.Typers;

public class XMLForms {

    public static Object parse(Class<?> type, IStreamInputSource source) {
        IXMLForm<?> xmlForm = Typers.getTyper(type, IXMLForm.class);
        if (xmlForm == null)
            return null;
        return null;
    }

}
