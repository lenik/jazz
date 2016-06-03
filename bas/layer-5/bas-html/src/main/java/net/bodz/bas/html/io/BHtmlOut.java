package net.bodz.bas.html.io;

import net.bodz.bas.io.BCharOut;

public class BHtmlOut
        extends RecHtmlOut {

    private final BCharOut buffer;
    private final HtmlOutputFormat format;

    public BHtmlOut() {
        this(new HtmlOutputFormat());
    }

    public BHtmlOut(HtmlOutputFormat format) {
        this(new BCharOut(16384), format);
    }

    BHtmlOut(BCharOut buf, HtmlOutputFormat format) {
        super(new HtmlDoc(buf, format));
        this.buffer = buf;
        this.format = format;
    }

    public BCharOut getBuffer() {
        return buffer;
    }

    public HtmlOutputFormat getFormat() {
        return format;
    }

    @Override
    public String toString() {
        flush();
        return buffer.toString();
    }

}
