package net.bodz.bas.c.java.io;

import java.io.File;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class FileTypers
        extends AbstractCommonTypers<File> {

    /**
     * The context cwd.
     */
    @ParameterType(File.class)
    public static final String textformContextDirectory = "textform.contextDirectory";
    public static final File defaultTextformContextDirectory = SystemColos.workdir.get();

    public FileTypers() {
        super(File.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public File parse(String text)
            throws ParseException {
        File file = new File(defaultTextformContextDirectory, text);
        return file;
    }

    @Override
    public File parse(String text, IOptions options)
            throws ParseException {
        File contextDirectory = options.get(textformContextDirectory, defaultTextformContextDirectory);
        File file = new File(contextDirectory, text);
        return file;
    }

}
