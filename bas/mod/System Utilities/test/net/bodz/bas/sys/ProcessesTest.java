package net.bodz.bas.sys;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class ProcessesTest {

    String charset = "utf-8"; //$NON-NLS-1$

    @Test
    public void testIocapProcessString() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                Process process = Processes.shellExec(input);
                // Runtime.getRuntime().exec(input);
                if (isBreakpoint())
                    System.err.println(input);
                String out = Processes.iocap(process, charset);
                out = out.replaceAll("\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
                return out;
            }
        }, //
                EQ("echo hello", "hello\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("echo world 1>&2", "world \n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("mtpulse C O1 X2 O3 X4 O5 X6 O7 X8 O9 Xa", "123456789a"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
