package net.bodz.bas.c.xml;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.tf.TypeFeatures;

public class XMLForms {

    public static Object parse(Class<?> type, IStreamInputSource source) {
        IXMLForm<?> xmlForm = TypeFeatures.getTypeFeature(type, IXMLForm.class);
        if (xmlForm == null)
            return null;
        return null;
    }

}
