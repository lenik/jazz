package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.io.out.ByteOuts;
import net.bodz.bas.io.out.IByteOut;
import net.bodz.bas.type.traits.impl.AbstractParser;

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
        return ByteOuts.get(out);
    }

}
