package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.mda.xjdoc.model.IElementDoc;

public abstract class AbstractXjdocElement
        // extends AbstractElement
        implements IXjdocElement {

    transient XjdocCache _cache;

    public AbstractXjdocElement() {
    }

    private XjdocCache _fillCache() {
        if (_cache == null)
            synchronized (this) {
                if (_cache == null) {
                    _cache = new XjdocCache();
                    _load(_cache);
                }
            }
        return _cache;
    }

    void _load(XjdocCache cache) {
        IElementDoc xjdoc = cache.xjdoc;
        if (xjdoc == null) {
            try {
                xjdoc = loadXjdoc();
            } catch (Exception e) {
                throw new LazyLoadException(e.getMessage(), e);
            }
            if (xjdoc == null)
                // xjdoc = IElementDoc.NULL;
                throw new IllegalUsageException("xjdoc isn't available.");

            _cache.xjdoc = xjdoc;
            XjdocModifier.process(xjdoc);
        }

        // New version: Use @label only.
        iString label = xjdoc.getTextTag(IElementDoc.LABEL);
        // if (label == null) label = xjdoc.getText().headPar();

        iString description = xjdoc.getTextTag(IElementDoc.DESCRIPTION);
        if (description == null)
            description = getXjdoc().getText().headPar();

        cache.label = label;
        cache.description = description;
        cache.helpDoc = xjdoc.getText();

        Priority aPriority = getClass().getAnnotation(Priority.class);
        if (aPriority != null)
            cache.priority = aPriority.value();

        DetailLevel aDetailLevel = getClass().getAnnotation(DetailLevel.class);
        if (aDetailLevel != null)
            cache.detailLevel = aDetailLevel.value();
    }

    /**
     * @return Non-<code>null</code> {@link IElementDoc}.
     * @throws IllegalUsageException
     *             If xjdoc isn't available.
     */
    @Override
    public IElementDoc getXjdoc() {
        return _fillCache().xjdoc;
    }

    @Override
    public void setXjdoc(IElementDoc xjdoc) {
        if (_cache == null)
            _cache = new XjdocCache();
        _cache.xjdoc = xjdoc;
        _load(_cache);
    }

    protected abstract IElementDoc loadXjdoc()
            throws ParseException, IOException;

    @Override
    public String getName() {
        String id = ObjectInfo.getSimpleId(this);
        return id;
    }

    @Override
    public iString getLabel() {
        return _fillCache().label;
    }

    protected void setLabel(iString label) {
        _fillCache().label = label;
    }

    /**
     * The default description is the header line of the text.
     */
    @Override
    public iString getDescription() {
        return _fillCache().description;
    }

    protected void setDescription(iString description) {
        _fillCache().description = description;
    }

    /**
     * The default helpDoc is from the text without the header line.
     */
    @Override
    public synchronized iString getHelpDoc() {
        return _fillCache().helpDoc;
    }

    protected void setHelpDoc(iString helpDoc) {
        _fillCache().helpDoc = helpDoc;
    }

    @Override
    public int getDetailLevel() {
        return _fillCache().detailLevel;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public int getPriority() {
        return _fillCache().priority;
    }

    @Override
    public String toString() {
        String name = getName();
        String label = Nullables.toString(getLabel());
        StringBuilder sb = new StringBuilder();
        if (label != null) {
            sb.append(label);
            sb.append(" (");
        }

        sb.append(name);
        // sb.append(": " + getPriority());

        if (label != null) {
            sb.append(')');
        }
        return sb.toString();
    }

}
