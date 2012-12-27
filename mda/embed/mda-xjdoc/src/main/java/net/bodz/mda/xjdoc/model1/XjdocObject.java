package net.bodz.mda.xjdoc.model1;

import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class XjdocObject
        extends AbstractXjdocElement {

    @Override
    public IJavaElementDoc loadXjdoc() {
        ClassDoc classDoc = ClassDocs.loadFromResource(getClass(), true);
        return classDoc;
    }

}
