package net.bodz.bas.c.java.nio.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.ctx.sys.UserDirVars;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class PathTypers
        extends AbstractCommonTypers<Path> {

    /**
     * The context cwd.
     */
    @ParameterType(Path.class)
    public static final String textformContextDirectoryKey = "textform.contextDirectory";
    public static final Path defaultTextformContextDirectory = UserDirVars.getInstance().get();

    public PathTypers() {
        super(Path.class);
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
    public Path parse(String text, IOptions options)
            throws ParseException {
        // TODO Lapiota Override:
        // if (text.startsWith("?"))
        // return ModulesRoot.DEFAULT.findexp(text.substring(1));

        Path file = Paths.get(text);
        if (file.isAbsolute())
            return file;

        Path contextDirectory = options.get(textformContextDirectoryKey, //
                defaultTextformContextDirectory);
        Path path = contextDirectory.resolve(text);
        return path;
    }

}
