package net.bodz.bas.types.parsers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class CharOutParser implements TypeParser {

    @Override
    public CharOut parse(String path) throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return CharOuts.get(out);
    }

}
