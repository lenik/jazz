package net.bodz.bas.cli.ext;

import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.ParseException;

public interface CLIPlugin {

    // static methods

    String getDescription();

    void help(CharOut out, String prefix);

    // instance methods

    void setParameters(Map<String, String> parameters) throws CLIException,
            ParseException;

}
