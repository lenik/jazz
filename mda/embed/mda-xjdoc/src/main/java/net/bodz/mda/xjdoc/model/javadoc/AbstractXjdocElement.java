package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractXjdocElement
        // extends AbstractElement
        implements IXjdocElement {

    private transient IElementDoc xjdoc;
    private transient boolean xjdocLoaded;

    private transient iString label;
    private transient iString description;
    private transient iString helpDoc;

    public AbstractXjdocElement() {
    }

    /**
     * @return Non-<code>null</code> {@link IElementDoc}.
     * @throws IllegalUsageException
     *             If xjdoc isn't available.
     */
    @Override
    public IElementDoc getXjdoc() {
        if (xjdoc == null) {
            synchronized (this) {
                if (!xjdocLoaded) {
                    try {
                        xjdoc = loadXjdoc();
                        XjdocModifier.process(xjdoc);
                    } catch (Exception e) {
                        throw new LazyLoadException(e.getMessage(), e);
                    }
                    xjdocLoaded = true;
                }
            }
            if (xjdoc == null)
                throw new IllegalUsageException("xjdoc isn't available.");
        }
        return xjdoc;
    }

    @Override
    public void setXjdoc(IElementDoc xjdoc) {
        this.xjdoc = xjdoc;
        this.xjdocLoaded = xjdoc != null;
    }

    protected IElementDoc loadXjdoc()
            throws ParseException, IOException {
        if (xjdoc != null)
            throw new IllegalStateException("already loaded.");
        else
            throw new NotImplementedException();
    }

    @Override
    public String getName() {
        String id = ObjectInfo.getSimpleId(this);
        return id;
    }

    @Override
    public iString getLabel() {
        if (label == null) {
            IElementDoc xjdoc = getXjdoc();
            label = xjdoc.getTextTag(IElementDoc.LABEL);

            if (label == null)
                label = xjdoc.getText().headPar();
        }
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
        if (description == null)
            description = getXjdoc().getTextTag(IElementDoc.DESCRIPTION);
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
            helpDoc = getXjdoc().getText();
            if (xjdoc.getTextTag(IElementDoc.LABEL) == null) {
                helpDoc = helpDoc.tailPar();
            }
        }
        return helpDoc;
    }

    protected void setHelpDoc(iString helpDoc) {
        this.helpDoc = helpDoc;
    }

    @Override
    public int getDetailLevel() {
        return DetailLevel.NORMAL;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
