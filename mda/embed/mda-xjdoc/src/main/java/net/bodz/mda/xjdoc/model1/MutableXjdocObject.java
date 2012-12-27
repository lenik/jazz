package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class MutableXjdocObject
        extends SemiMutableXjdocElement {

    private static final long serialVersionUID = 1L;

    transient boolean xjdocLoaded;

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

    @Override
    protected IJavaElementDoc loadXjdoc() {
        ClassDoc classDoc = ClassDocs.loadFromResource(getClass(), true);
        return classDoc;
    }

}
