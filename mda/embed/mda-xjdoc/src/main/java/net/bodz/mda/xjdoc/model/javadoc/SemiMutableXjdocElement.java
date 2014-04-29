package net.bodz.mda.xjdoc.model.javadoc;

import java.io.Serializable;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom1.SemiMutableElement;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class SemiMutableXjdocElement
        extends SemiMutableElement
        implements IXjdocElement, Serializable {

    private static final long serialVersionUID = 1L;

    transient IElementDoc xjdoc;
    transient boolean xjdocLoaded;

    /**
     * @return Non-<code>null</code> {@link IElementDoc}.
     */
    public IElementDoc getXjdoc() {
        if (xjdoc == null) {
            synchronized (this) {
                if (!xjdocLoaded) {
                    xjdoc = loadXjdoc();
                    xjdocLoaded = true;
                }
            }
            if (xjdoc == null)
                throw new IllegalUsageException("Artifact doc isn't set.");
        }
        return xjdoc;
    }

    protected IElementDoc loadXjdoc() {
        return null;
    }

    @Override
    public void setXjdoc(IElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = true;
    }

}
