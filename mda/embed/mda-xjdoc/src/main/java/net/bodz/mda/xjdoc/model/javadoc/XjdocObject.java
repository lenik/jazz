package net.bodz.mda.xjdoc.model.javadoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.mda.xjdoc.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class XjdocObject
        extends AbstractXjdocElement {

    static final Logger logger = LoggerFactory.getLogger(XjdocObject.class);

    @Override
    public IJavaElementDoc loadXjdoc() {
        Class<?> clazz = getClass();
        ClassDoc classDoc = ClassDocLoader.load(clazz);

        if (classDoc == null) {
            logger.warn("No class doc available for " + clazz);
            classDoc = new ClassDoc(clazz.getName());
        }

        return classDoc;
    }

}
