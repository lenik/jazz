package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.i18n.dom1.IMutableElement;
import net.bodz.mda.xjdoc.model.IElementDoc;

public interface IXjdocElement
        extends IElement, IXjdocAware {

    class fn
            extends IElement.fn {

        public static final void initPropsFromXjdoc(IMutableElement element, IElementDoc doc) {
            iString label = doc.getTextTag(IElementDoc.LABEL);
            if (label != null)
                element.setLabel(label);

            iString description = doc.getTextTag(IElementDoc.DESCRIPTION);
            if (description != null)
                element.setDescription(description);

            iString text = doc.getText();
            if (text != null)
                element.setHelpDoc(text);
        }

        public static final String labelName(IXjdocElement element) {
            return labelName(element, false);
        }

        public static final String labelName(IXjdocElement element, boolean allowEmpty) {
            iString label = element.getLabel();
            if (label != null) {
                String labelStr = label.toString();
                if (labelStr != null)
                    if (allowEmpty || !labelStr.isEmpty())
                        return labelStr;
            }

            IElementDoc xjdoc = element.getXjdoc();
            if (xjdoc != null) {
                iString text = xjdoc.getText();
                if (text != null) {
                    String headPar = text.getHeadPar();
                    if (headPar != null)
                        if (allowEmpty || !headPar.isEmpty())
                            return headPar;
                }
            }

            return element.getName();
        }

    }

}
