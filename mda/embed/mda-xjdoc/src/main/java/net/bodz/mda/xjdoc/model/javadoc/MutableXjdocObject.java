package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableXjdocObject
        extends SemiMutableXjdocElement {

    private static final long serialVersionUID = 1L;

    transient boolean xjdocLoaded;

    /**
     * @return Non-<code>null</code> {@link IElementDoc}.
     */
    @Override
    public IElementDoc getXjdoc() {
        if (xjdoc == null) {
            synchronized (this) {
                if (!xjdocLoaded) {
                    xjdoc = loadXjdoc();
                    xjdocLoaded = true;
                }
            }
        }
        return xjdoc;
    }

    @Override
    protected IElementDoc loadXjdoc() {
        ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(getClass());

        if (classDoc == null)
            throw new IllegalUsageException("No class doc available.");

        return classDoc;
    }

}
