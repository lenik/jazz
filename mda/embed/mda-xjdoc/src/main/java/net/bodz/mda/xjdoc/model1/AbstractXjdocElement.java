package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom1.AbstractElement;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public abstract class AbstractXjdocElement
        extends AbstractElement
        implements IXjdocElement {

    private transient IJavaElementDoc xjdoc;
    private transient boolean xjdocLoaded;

    private transient DomainString displayName;
    private transient DomainString description;
    private transient DomainString helpDoc;

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
    public DomainString getDisplayName() {
        if (displayName == null)
            displayName = getXjdoc().getDisplayName();
        return displayName;
    }

    /**
     * The default description is the header line of the text.
     */
    @Override
    public synchronized DomainString getDescription() {
        if (description == null) {
            DomainString text = getXjdoc().getText();
            if (text != null)
                description = text.headPar();
        }
        return description;
    }

    /**
     * The default helpDoc is from the text without the header line.
     */
    @Override
    public synchronized DomainString getHelpDoc() {
        if (helpDoc == null) {
            DomainString text = getXjdoc().getText();
            if (text != null)
                helpDoc = text.tailPar();
        }
        return helpDoc;
    }

    protected void setXjdoc(IJavaElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = true;
    }

    protected void setDisplayName(DomainString displayName) {
        this.displayName = displayName;
    }

    protected void setDescription(DomainString description) {
        this.description = description;
    }

    protected void setHelpDoc(DomainString helpDoc) {
        this.helpDoc = helpDoc;
    }

}
