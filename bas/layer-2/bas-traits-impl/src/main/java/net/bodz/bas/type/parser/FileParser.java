package net.bodz.bas.type.parser;

import java.io.File;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class FileParser
        extends AbstractParser<File> {

    @Override
    public File parse(String path)
            throws ParseException {
        File cwd = SystemContextFactory.getSystemContext().getWorkingDirectoryContext().getWorkingDirectory();
        File file = new File(cwd, path);
        return file;
    }

    public static void main(String[] args) {
        File cwd = SystemContextFactory.getSystemContext().getWorkingDirectoryContext().getWorkingDirectory();
        
        System.out.println(new File(cwd, "/boot.ini"));
    }

}
