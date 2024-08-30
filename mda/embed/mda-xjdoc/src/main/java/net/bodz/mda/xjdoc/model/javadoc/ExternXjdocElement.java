package net.bodz.mda.xjdoc.model.javadoc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.IMutableElementDoc;

public class ExternXjdocElement
        extends AbstractXjdocElement {

    static final Logger logger = LoggerFactory.getLogger(ExternXjdocElement.class);

    public ExternXjdocElement(IElementDoc doc) {
        if (doc == null)
            throw new NullPointerException("doc");
        setXjdoc(doc);
    }

    @Override
    protected IMutableElementDoc loadXjdoc()
            throws ParseException, IOException {
        return null;
    }

}
