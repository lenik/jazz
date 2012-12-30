package net.bodz.mda.xjdoc.model.javadoc;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class MutableXjdocElement
        extends MutableElement
        implements IXjdocElement, Serializable {

    private static final long serialVersionUID = 1L;

    IJavaElementDoc xjdoc;

    /**
     * @return Non-<code>null</code> {@link IJavaElementDoc}.
     */
    @Override
    public IJavaElementDoc getXjdoc() {
        return xjdoc;
    }

    public void setXjdoc(IJavaElementDoc xjdoc, boolean applyToElementProperties) {
        this.xjdoc = xjdoc;

        if (applyToElementProperties) {
            if (xjdoc == null)
                throw new NullPointerException("xjdoc");

            setName(xjdoc.getName());
            setLabel(xjdoc.getLabel());

            DomainString text = xjdoc.getText();
            if (text != null) {
                setDescription(text.headPar());
                setHelpDoc(text.tailPar());
            }
        }
    }

}
