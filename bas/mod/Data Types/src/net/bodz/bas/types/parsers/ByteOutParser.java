package net.bodz.bas.types.parsers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.io.ByteOuts;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class ByteOutParser implements TypeParser {

    @Override
    public IByteOut parse(String path) throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return ByteOuts.get(out);
    }

}
