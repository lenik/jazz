package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.*;
import net.bodz.bas.sio.util.EncodedByteIn;

public class CharsResource
        extends AbstractStreamResource {

    StringWriter chars;

    public CharsResource() {
        this(null);
    }

    public CharsResource(StringWriter chars) {
        this.chars = chars;
    }

    public boolean isAllocated() {
        return chars != null;
    }

    public void unallocate() {
        chars = null;
    }

    public String getData() {
        if (chars == null)
            return null;
        else
            return chars.getBuffer().toString();
    }

    public int getLength() {
        if (chars == null)
            return 0;
        else
            return chars.getBuffer().length();
    }

    /**
     * @return {@link Writer} with {@link Writer#close()} filtered out.
     */
    @Override
    protected Writer _newWriter()
            throws IOException {
        if (chars == null)
            chars = new StringWriter();
        if (!isAppendMode())
            chars.getBuffer().setLength(0);
        return chars;
    }

    @Override
    protected OutputStream _newOutputStream()
            throws IOException {
        return new WriterOutputStream(_newWriter(), getCharset());
    }

    @Override
    protected IByteOut _newByteOut()
            throws IOException {
        return new OutputStreamByteOut(_newOutputStream());
    }

    @Override
    protected ICharOut _newCharOut()
            throws IOException {
        return _newPrintOut();
    }

    @Override
    protected IPrintOut _newPrintOut()
            throws IOException {
        return new WriterPrintOut(_newWriter());
    }

    @Override
    protected ICharIn _newCharIn()
            throws IOException {
        String snapshot = chars.toString();
        return new StringCharIn(snapshot);
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        ICharIn charIn = _newCharIn();
        EncodedByteIn byteIn = new EncodedByteIn(charIn, getCharset());
        return byteIn;
    }

}
