package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class XjdocObject
        extends AbstractXjdocElement {

    private static final Logger _logger = LoggerFactory.getLogger(XjdocObject.class);

    @Override
    protected IElementDoc loadXjdoc()
            throws XjdocLoaderException, ParseException, IOException {
        Class<?> clazz = getClass();
        ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(clazz);

        if (classDoc == null) {
            _logger.warn("No class doc available for " + clazz);
            classDoc = new ClassDoc(Xjdocs.getDefaultTagLibrary(), clazz.getName());
        }

        return classDoc;
    }

}
