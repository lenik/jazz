package net.bodz.bas.html.io;

import java.io.IOException;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.PrintException;

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
        super(new HtmlDoc((ITreeOut) buf, format));
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
