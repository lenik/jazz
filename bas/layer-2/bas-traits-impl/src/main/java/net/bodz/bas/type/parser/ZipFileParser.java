package net.bodz.bas.type.parser;

import java.io.File;
import java.util.zip.ZipFile;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class ZipFileParser
        extends AbstractParser<ZipFile> {

    @Override
    public ZipFile parse(String path)
            throws ParseException {

        File cwd = SystemContextFactory.getSystemContext().getWorkingDirectoryContext().getWorkingDirectory();
        File file = new File(cwd, path);
        try {
            return new ZipFile(file);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

}
