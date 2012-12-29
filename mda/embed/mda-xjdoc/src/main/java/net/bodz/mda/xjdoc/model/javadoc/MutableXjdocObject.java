package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.conv.ClassDocLoader;
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
        }
        return xjdoc;
    }

    @Override
    protected IJavaElementDoc loadXjdoc() {
        ClassDoc classDoc = ClassDocLoader.load(getClass());

        if (classDoc == null)
            throw new IllegalUsageException("No class doc available.");

        return classDoc;
    }

}
