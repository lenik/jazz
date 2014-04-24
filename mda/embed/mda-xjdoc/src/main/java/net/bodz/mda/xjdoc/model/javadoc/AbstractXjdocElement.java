package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NotImplementedException;
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
     * @throws IllegalUsageException
     *             If xjdoc isn't available.
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
                throw new IllegalUsageException("xjdoc isn't available.");
        }
        return xjdoc;
    }

    @Override
    public void setXjdoc(IJavaElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = xjdoc != null;
    }

    protected IJavaElementDoc loadXjdoc() {
        throw new NotImplementedException();
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

    protected void setLabel(iString label) {
        this.label = label;
    }

    /**
     * The default description is the header line of the text.
     */
    @Override
    public synchronized iString getDescription() {
        if (description == null) {
            iString text = getXjdoc().getText();
            description = fn.extractDescription(text);
        }
        return description;
    }

    protected void setDescription(iString description) {
        this.description = description;
    }

    /**
     * The default helpDoc is from the text without the header line.
     */
    @Override
    public synchronized iString getHelpDoc() {
        if (helpDoc == null) {
            iString text = getXjdoc().getText();
            helpDoc = fn.extractHelpDoc(text);
        }
        return helpDoc;
    }

    protected void setHelpDoc(iString helpDoc) {
        this.helpDoc = helpDoc;
    }

}
