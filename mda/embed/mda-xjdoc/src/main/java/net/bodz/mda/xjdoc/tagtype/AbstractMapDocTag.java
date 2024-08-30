package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.model.AbstractMultiDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public abstract class AbstractMapDocTag<E>
        extends AbstractMultiDocTag<E> {

    public AbstractMapDocTag() {
        super();
    }

    public AbstractMapDocTag(IContainer<E> data) {
        super(data);
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        E item = parseJavadoc(javadoc);
        data.addNamed(item, extension);
    }

    @Override
    public void writeJavadoc(String name, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        for (String key : data.names()) {
            E item = data.get(key);
            String javadoc = formatJavadoc(item);
            writer.writeTag(name + "." + key, javadoc);
        }
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String text, IOptions options)
            throws ParseException {
        E item = parseFlatf(text);
        data.addNamed(item, extension);
    }

    @Override
    public void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException, FormatException {
        for (String name : data.names()) {
            String extension = name;
            E value = data.get(name);
            if (value != null) {
                String s = formatFlatf(out, value);
                out.attribute(attributeName + "." + extension, s);
            }
        }
    }

    protected abstract E parseJavadoc(String s)
            throws ParseException;

    protected abstract String formatJavadoc(E value);

    protected abstract E parseFlatf(String s)
            throws ParseException;

    protected abstract String formatFlatf(IFlatfOutput out, E value);

}
