package net.bodz.bas.type.parser.x;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;
import net.bodz.bas.io.ByteOuts;
import net.bodz.bas.io.IByteOut;

public class ByteOutParser extends TypeParser {

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
