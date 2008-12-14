package net.bodz.bas.types.parsers;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class ZipFileParser implements TypeParser {

    @Override
    public ZipFile parse(String path) throws ParseException {
        File file = Files.canoniOf(path);
        try {
            return new ZipFile(file);
        } catch (ZipException e) {
            throw new ParseException(e);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

}
