package net.bodz.bas.eclipse.cli;
 
import java.util.Map;

import org.eclipse.equinox.app.IApplicationContext;

class Util {

    private static final String[] EMPTY_ARGS = {};

    @SuppressWarnings("unchecked")
    public static String[] getArguments(IApplicationContext context) {
        Map arguments = context.getArguments();
        Object _args = arguments.get(IApplicationContext.APPLICATION_ARGS);
        if (_args == null)
            return EMPTY_ARGS;
        return (String[]) _args;
    }

}
