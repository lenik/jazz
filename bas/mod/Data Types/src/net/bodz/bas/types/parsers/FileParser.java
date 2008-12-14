package net.bodz.bas.types.parsers;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class FileParser implements TypeParser {

    @Override
    public File parse(String path) throws ParseException {
        return Files.canoniOf(path);
    }

}
