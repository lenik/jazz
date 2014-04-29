package net.bodz.mda.xjdoc.model.javadoc;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.MutableElement;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableXjdocElement
        extends MutableElement
        implements IXjdocElement, Serializable {

    private static final long serialVersionUID = 1L;

    IElementDoc xjdoc;

    /**
     * @return Non-<code>null</code> {@link IElementDoc}.
     */
    @Override
    public IElementDoc getXjdoc() {
        return xjdoc;
    }

    @Override
    public void setXjdoc(IElementDoc xjdoc) {
        setXjdoc(xjdoc, false);
    }

    public void setXjdoc(IElementDoc xjdoc, boolean applyToElementProperties) {
        this.xjdoc = xjdoc;

        if (applyToElementProperties) {
            if (xjdoc == null)
                throw new NullPointerException("xjdoc");

            setName(xjdoc.getName());
            setLabel(xjdoc.getLabel());

            iString text = xjdoc.getText();
            setDescription(text.headPar());
            setHelpDoc(text.tailPar());
        }
    }

}
