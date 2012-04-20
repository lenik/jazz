package net.bodz.bas.xml;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.trait.Traits;

public class XMLForms {

    public static Object parse(Class<?> type, IStreamInputSource source) {
        IXMLForm<?> xmlForm = Traits.getTrait(type, IXMLForm.class);
        if (xmlForm == null)
            return null;
        return null;
    }

}
