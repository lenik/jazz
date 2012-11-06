package net.bodz.bas.cli.model;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.cli.skel.CLISyntaxException;

public class ParseOptionException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    IOption option;

    public ParseOptionException(IOption option, String... optionArgs) {
        super(String.format("Bad option %s parameters: %s", option.getName(), formatArgs(optionArgs)));
        this.option = option;
    }

    public ParseOptionException(IOption option, String[] optionArgs, Throwable cause) {
        super(String.format("Bad option %s parameters: %s", option.getName(), formatArgs(optionArgs)), cause);
        this.option = option;
    }

    static String formatArgs(String... args) {
        return StringArray.join(", ", args);
    }

}
