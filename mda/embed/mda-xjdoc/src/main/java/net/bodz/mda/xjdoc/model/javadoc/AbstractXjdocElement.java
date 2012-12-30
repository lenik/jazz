package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public abstract class AbstractXjdocElement
        extends AbstractElement
        implements IXjdocElement {

    private transient IJavaElementDoc xjdoc;
    private transient boolean xjdocLoaded;

    private transient iString label;
    private transient iString description;
    private transient iString helpDoc;

    public AbstractXjdocElement() {
    }

    /**
     * @return Non-<code>null</code> {@link IJavaElementDoc}.
     */
    @Override
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

    @Override
    public String getName() {
        return getXjdoc().getName();
    }

    @Override
    public iString getLabel() {
        if (label == null)
            label = getXjdoc().getLabel();
        return label;
    }

    /**
     * The default description is the header line of the text.
     */
    @Override
    public synchronized iString getDescription() {
        if (description == null) {
            iString text = getXjdoc().getText();
            if (text != null)
                description = text.headPar();
        }
        return description;
    }

    /**
     * The default helpDoc is from the text without the header line.
     */
    @Override
    public synchronized iString getHelpDoc() {
        if (helpDoc == null) {
            iString text = getXjdoc().getText();
            if (text != null)
                helpDoc = text.tailPar();
        }
        return helpDoc;
    }

    protected void setXjdoc(IJavaElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = true;
    }

    protected void setLabel(iString label) {
        this.label = label;
    }

    protected void setDescription(iString description) {
        this.description = description;
    }

    protected void setHelpDoc(iString helpDoc) {
        this.helpDoc = helpDoc;
    }

}
