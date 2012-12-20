package net.bodz.bas.c.java.io.capture;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FilePath;

public class ProcessesTest
        extends Assert {

    String charset = "utf-8";

    String iocap(String... cmdv)
            throws Exception {
        Process process = Processes.shellExec(cmdv);
        String out = Processes.iocap(process, charset);
        out = out.replaceAll("\r\n", "\n");
        return out;
    }

    @Test
    public void testIocapProcessString()
            throws Exception {
        assertEquals("hello\n", iocap("echo hello"));
        assertEquals("world\n", iocap("echo world 1>&2"));
    }

    @Test
    public void testMtpulse()
            throws Exception {
        File mtpulse = FilePath.which("mtpulse");
        if (mtpulse != null) {
            System.out.println("mtpulse: " + mtpulse);
            assertEquals("123456789a", iocap("mtpulse C O1 X2 O3 X4 O5 X6 O7 X8 O9 Xa"));
        }
    }

}
