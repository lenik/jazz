package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.coll.ListContainer;
import net.bodz.mda.xjdoc.model.AbstractMultiDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public abstract class RepeatDocTag<E>
        extends AbstractMultiDocTag<E> {

    static final Logger logger = LoggerFactory.getLogger(RepeatDocTag.class);

    public RepeatDocTag() {
        super();
        data = new ListContainer<E>(elementType);
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        E element = parse(javadoc);
        if (element == null)
            throw new NullPointerException("element (tagName=" + tagName + ")");
        data.add(element);
    }

    @Override
    public void writeJavadoc(String name, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        for (E element : data)
            writer.writeTag(name, format(element));
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String text, IOptions options)
            throws ParseException {
        E element = parse(text);
        data.add(element);
    }

    @Override
    public void writeObject(IFlatfOutput out, String name, IOptions options)
            throws IOException, FormatException {
        int index = 0;
        for (E element : data) {
            String str = format(element);
            out.attribute(name + "." + index, str);
            index++;
        }
    }

    protected abstract E parse(String s)
            throws ParseException;

    protected abstract String format(E value);

}
