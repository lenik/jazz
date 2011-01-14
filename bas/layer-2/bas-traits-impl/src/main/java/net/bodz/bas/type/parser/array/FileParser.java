package net.bodz.bas.type.parser.array;

import java.io.File;

import net.bodz.bas.context.clg.SystemCLG;
import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class FileParser
        extends AbstractParser<File> {

    @Override
    public File parse(String path)
            throws ParseException {
        File cwd = SystemCLG.cwd.get();
        File file = new File(cwd, path);
        return file;
    }

}
