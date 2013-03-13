package net.bodz.bas.program.skel;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.program.model.HelpPageFormatter;

import user.HelloProgram;

public class BasicCLITest
        extends Assert {

    {
        Locale.setDefault(Locale.CHINA);
    }

    @Test
    public void printOptions()
            throws Exception {
        HelloProgram program = new HelloProgram();
        // program.execute("-h");
        HelpPageFormatter pageFormatter = new HelpPageFormatter();
        String s = pageFormatter.format(program);
        System.out.println(s);
    }

}
