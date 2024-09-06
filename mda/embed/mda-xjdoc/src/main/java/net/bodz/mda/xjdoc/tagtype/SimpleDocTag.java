package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.model.AbstractDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public abstract class SimpleDocTag<T>
        extends AbstractDocTag<T> {

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        data = parse(javadoc);
    }

    @Override
    public void writeJavadoc(String name, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        if (data != null) {
            String s = format(data);
            writer.writeTag(name, s);
        }
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String string, IOptions options)
            throws ParseException {
        data = parse(string);
    }

    @Override
    public void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException, FormatException {
        if (data != null) {
            String string = format(data);
            out.attribute(attributeName, string);
        }
    }

    protected abstract T parse(String s)
            throws ParseException;

    protected abstract String format(T value);

}
