package net.bodz.lily.tool.wsdoc;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class DocUtil {

    public static String getLabel(IElementDoc doc) {

        if (doc == null)
            return null;
        iString label = (iString) doc.getTag("label");
        if (label != null)
            return label.toString();

        iString text = doc.getText();
        if (text != null)
            return text.getHeadPar();

        return null;
    }

}
