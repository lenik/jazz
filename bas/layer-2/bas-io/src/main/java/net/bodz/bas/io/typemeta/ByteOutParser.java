package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.OutputStreamByteOut;
import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class ByteOutParser
        extends AbstractParser<IByteOut> {

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
