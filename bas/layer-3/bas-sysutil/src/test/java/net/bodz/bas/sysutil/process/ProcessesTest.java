package net.bodz.bas.sysutil.process;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.sysutil.process.Processes;

import org.junit.Test;

public class ProcessesTest {

    String charset = "utf-8"; //$NON-NLS-1$

    String iocap(String cmdline) throws Exception {
        Process process = Processes.shellExec(cmdline);
        String out = Processes.iocap(process, charset);
        out = out.replaceAll("\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        return out;
    }

    @Test
    public void testIocapProcessString() throws Exception {
        assertEquals("hello\n", iocap("echo hello"));
        assertEquals("world \n", iocap("echo world 1>&2"));
    }

    @Test
    public void testMtpulse() throws Exception {
        File mtpulse = Files.which("mtpulse");
        if (mtpulse != null) {
            System.out.println("mtpulse: " + mtpulse);
            assertEquals("123456789a", iocap("mtpulse C O1 X2 O3 X4 O5 X6 O7 X8 O9 Xa"));
        }
    }

}
