package net.bodz.bas.cli.util;

import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;

import org.apache.commons.lang.ArrayUtils;

public class CLILauncher {

    public static void main(String[] args)
            throws Throwable {
        String cliClassName = args[0];
        args = (String[]) ArrayUtils.remove(args, 0);

        Class<?> cliClass = Class.forName(cliClassName);
        if (!BasicCLI.class.isAssignableFrom(cliClass))
            throw new IllegalArgumentException("Not a CLI class: " + cliClass);
        BasicCLI cli = (BasicCLI) Jdk7Reflect.newInstance(cliClass);

        cli.execute(args);
    }

}
