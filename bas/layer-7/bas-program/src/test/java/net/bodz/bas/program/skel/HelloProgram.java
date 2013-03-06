package net.bodz.bas.program.skel;

/**
 * Hello Program
 */
public class HelloProgram
        extends BasicCLI {

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + ". " + args[i] + ";");
        }
    }

    public static void main(String[] args)
            throws Exception {
        new HelloProgram().execute("-h");
    }
    
}
