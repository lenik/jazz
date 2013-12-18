package user;

import java.util.Map.Entry;

import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.program.meta.ProgramName;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Hello Program
 */
@MainVersion({ 0, 0 })
@ProgramName("hello")
public class HelloProgram
        extends BasicCLI {

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        System.out.println("Arguments: ");
        for (int index = 0; index < args.length; index++)
            System.out.printf("    %d. %s;\n", index, args[index]);

        System.out.println("Variable Map: ");
        for (Entry<String, Object> entry : getVariableMap().entrySet())
            System.out.printf("    %s = %s;\n", entry.getKey(), entry.getValue());
    }

    public static void main(String[] args)
            throws Exception {
        new HelloProgram().execute(args);
    }

}
