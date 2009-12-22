package net.bodz.bas.io.typeparsers;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class ZipFileParser extends TypeParser {

    @Override
    public ZipFile parse(String path) throws ParseException {
        File file = CWD.get(path);
        try {
            return new ZipFile(file);
        } catch (ZipException e) {
            throw new ParseException(e);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

}
