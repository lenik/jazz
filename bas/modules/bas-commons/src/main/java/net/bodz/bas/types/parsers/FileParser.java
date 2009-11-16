package net.bodz.bas.types.parsers;

import java.io.File;

import net.bodz.bas.io.CWD;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class FileParser implements TypeParser {

    @Override
    public File parse(String path) throws ParseException {
        return CWD.get(path);
    }

}
