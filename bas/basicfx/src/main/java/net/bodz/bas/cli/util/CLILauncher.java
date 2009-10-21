package net.bodz.bas.cli.util;

import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.util.ArrayOps;

public class CLILauncher {

    public static void main(String[] args) throws Throwable {
        String cliClassName = args[0];
        Class<?> cliClass = Class.forName(cliClassName);
        if (!BasicCLI.class.isAssignableFrom(cliClass))
            throw new IllegalArgumentException("Not a CLI class: " + cliClass); //$NON-NLS-1$
        BasicCLI cli = (BasicCLI) Reflects.newInstance(cliClass);

        args = ArrayOps.Strings.shift(args);
        cli.run(args);
    }

}
