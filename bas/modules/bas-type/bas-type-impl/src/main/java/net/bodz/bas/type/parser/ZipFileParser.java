package net.bodz.bas.type.parser;

import java.io.File;
import java.util.zip.ZipFile;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.sysctx.SystemContextFactory;
import net.bodz.bas.type.traits.impl.AbstractParser;

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
