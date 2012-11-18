package net.bodz.bas.cli.plugin;

import java.util.Map;

import net.bodz.bas.cli.skel.CLISyntaxException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.sio.IPrintOut;

/**
 * Implementations must have at least a default constructor, while more constructors may be useful
 * to receive cli-arguments.
 */
public interface ICLIPlugin
        extends IElement {

    void formatHelpText(IPrintOut out, String prefix);

    /**
     * Parameters is defined in System.properties.
     */
    @Deprecated
    void initialize();

    /**
     * @see #boot()
     */
    void setParameters(Map<String, Object> parameters)
            throws CLISyntaxException, ParseException;

}
