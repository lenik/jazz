package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;
import java.util.logging.Logger;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.XjdocLoaderException;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IMutableElementDoc;

public class XjdocObject
        extends AbstractXjdocElement {

    // private static final Logger _logger = LoggerFactory.getLogger(XjdocObject.class);
    private static final Logger _logger = Logger.getLogger(XjdocObject.class.getName());

    @Override
    protected IMutableElementDoc loadXjdoc()
            throws XjdocLoaderException, ParseException, IOException {
        Class<?> clazz = getClass();
        ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(clazz);

        if (classDoc == null) {
            _logger.warning("No class doc available for " + clazz);
            classDoc = new ClassDoc(Xjdocs.getDefaultTagLibrary(), clazz.getName());
        }

        return classDoc;
    }

}
