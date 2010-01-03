package net.bodz.bas.type.parser.x;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;

public class CharOutParser extends TypeParser {

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
