package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.StringCharIn;
import net.bodz.bas.sio.WriterPrintOut;

public class CharsResource
        extends AbstractTextStreamResource {

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
    protected Writer _newWriter(boolean append)
            throws IOException {
        if (chars == null)
            chars = new StringWriter();
        if (!append) // CharsResource append
            chars.getBuffer().setLength(0);
        return chars;
    }

    @Override
    protected ICharOut _newCharOut(boolean append)
            throws IOException {
        return _newPrintOut(append);
    }

    @Override
    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        return new WriterPrintOut(_newWriter(append));
    }

    @Override
    protected ICharIn _newCharIn()
            throws IOException {
        String snapshot = chars.toString();
        return new StringCharIn(snapshot);
    }

}
