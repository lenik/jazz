package net.bodz.bas.cli.ext;

import java.util.Map;

import net.bodz.bas.cli.CLIException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.ParseException;

/**
 * Implementations must have at least a default constructor, while more
 * constructors may be useful to receive cli-arguments.
 */
public interface CLIPlugin {

    // static methods

    String getDescription();

    void help(CharOut out, String prefix);

    // instance methods

    /**
     * @see #boot()
     */
    void setParameters(Map<String, Object> parameters) throws CLIException,
            ParseException;

    /**
     * Parameters is defined in System.properties.
     */
    @Deprecated
    void boot() throws CLIException, ParseException;

}
