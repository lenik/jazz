package net.bodz.bas.cli.boot;

import org.apache.commons.lang.ArrayUtils;

import net.bodz.bas.cli.skel.BasicCLI;

public class CLILauncher {

    public static void main(String[] args)
            throws Throwable {
        String cliClassName = args[0];
        args = (String[]) ArrayUtils.remove(args, 0);

        Class<?> cliClass = Class.forName(cliClassName);
        if (!BasicCLI.class.isAssignableFrom(cliClass))
            throw new IllegalArgumentException("Not a CLI class: " + cliClass);
        BasicCLI cli = (BasicCLI) cliClass.newInstance();

        cli.execute(args);
    }

}
