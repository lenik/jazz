package net.bodz.bas.io.typeparsers;

import java.io.File;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class FileParser extends TypeParser {

    @Override
    public File parse(String path) throws ParseException {
        return CWD.get(path);
    }

}
