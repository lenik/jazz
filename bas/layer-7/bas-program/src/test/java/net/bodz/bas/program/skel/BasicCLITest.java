package net.bodz.bas.program.skel;

import org.junit.Assert;

import net.bodz.bas.program.model.HelpPageFormatter;

public class BasicCLITest
        extends Assert {

    public static void main(String[] args)
            throws Exception {
        HelloProgram program = new HelloProgram();
        // program.execute("-h");
        HelpPageFormatter pageFormatter = new HelpPageFormatter();
        String s = pageFormatter.format(program);
        System.out.println(s);
    }

}
