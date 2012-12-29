package net.bodz.mda.xjdoc.model1;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.mda.xjdoc.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class XjdocObject
        extends AbstractXjdocElement {

    @Override
    public IJavaElementDoc loadXjdoc() {
        ClassDoc classDoc = ClassDocLoader.load(getClass());

        if (classDoc == null)
            throw new IllegalUsageException("No class doc available.");

        return classDoc;
    }

}
