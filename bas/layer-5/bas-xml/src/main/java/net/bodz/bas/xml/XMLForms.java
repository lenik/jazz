package net.bodz.bas.xml;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.traits.Traits;

public class XMLForms {

    public static Object parse(Class<?> type, IStreamInputSource source) {
        IXMLForm<?> xmlForm = Traits.getTraits(type, IXMLForm.class);
        if (xmlForm == null)
            return null;
        return null;
    }

}
