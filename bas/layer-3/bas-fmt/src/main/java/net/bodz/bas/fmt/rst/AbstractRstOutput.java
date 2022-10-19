package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.t.set.FramedMarks;

public abstract class AbstractRstOutput
        implements
            IRstOutput {

    private String nullValue = null; // "null";
    private FramedMarks marks = new FramedMarks();

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    @Override
    public FramedMarks getMarks() {
        return marks;
    }

    @Override
    public void element(String name, IRstForm child, String... args)
            throws IOException, FormatException {
        if (child == null)
            return;

        beginElement(name, args);
        child.writeObject(this);
        endElement();
    }

    @Override
    public void attribute(String name, Object value)
            throws IOException {
        String s;
        if (value == null)
            if (nullValue == null)
                return;
            else
                s = nullValue;
        else
            s = StringEscape.escapeJava(value.toString());
        attributeRaw(name, s);
    }

    @Override
    public void attributeQuoted(String name, Object value)
            throws IOException {
        String s;
        if (value == null)
            if (nullValue == null)
                return;
            else
                s = nullValue;
        else
            s = StringQuote.qqJavaString(value.toString());
        attributeRaw(name, s);
    }

}
