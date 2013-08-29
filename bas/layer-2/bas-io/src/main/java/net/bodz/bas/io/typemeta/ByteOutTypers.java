package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class ByteOutTypers
        extends AbstractCommonTypers<IByteOut> {

    public ByteOutTypers() {

        super(IByteOut.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
            return this;
        return null;
    }

    @Override
    public IByteOut parse(String path)
            throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return new OutputStreamByteOut(out);
    }

}
