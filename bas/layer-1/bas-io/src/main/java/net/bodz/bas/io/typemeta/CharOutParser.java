package net.bodz.bas.io.typemeta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.io.out.CharOut;
import net.bodz.bas.io.out.CharOuts;
import net.bodz.bas.type.traits.AbstractParser;

public class CharOutParser
        extends AbstractParser<CharOut> {

    @Override
    public CharOut parse(String path)
            throws ParseException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return CharOuts.get(out);
    }

}
