package net.bodz.bas.cli.ext;

import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.util.Plugin;

/**
 * Implementations must have at least a default constructor, while more constructors may be useful
 * to receive cli-arguments.
 */
public interface CLIPlugin extends Plugin {

    // static methods

    void help(CharOut out, String prefix);

    // instance methods

    /**
     * @see #boot()
     */
    void setParameters(Map<String, Object> parameters) throws CLIException, ParseException;

}
