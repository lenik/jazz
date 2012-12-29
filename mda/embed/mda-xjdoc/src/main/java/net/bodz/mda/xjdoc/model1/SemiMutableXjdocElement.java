package net.bodz.mda.xjdoc.model1;

import java.io.Serializable;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom1.SemiMutableElement;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public abstract class SemiMutableXjdocElement
        extends SemiMutableElement
        implements IXjdocElement, Serializable {

    private static final long serialVersionUID = 1L;

    transient IJavaElementDoc xjdoc;
    transient boolean xjdocLoaded;

    /**
     * @return Non-<code>null</code> {@link IJavaElementDoc}.
     */
    public IJavaElementDoc getXjdoc() {
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

    protected IJavaElementDoc loadXjdoc() {
        return null;
    }

    protected void setXjdoc(IJavaElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = true;
    }

}
