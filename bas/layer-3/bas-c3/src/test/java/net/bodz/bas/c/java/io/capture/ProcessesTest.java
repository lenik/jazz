package net.bodz.bas.c.java.io.capture;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.system.SystemEnviron;
import net.bodz.bas.c.system.SystemProperties;

import org.junit.Test;

public class ProcessesTest {

    /**
     * @see net.bodz.bas.files.FileWhich
     */
    static File which(String program) {
        String path = SystemEnviron.getPATH();
        String pathSeparator = SystemProperties.getPathSeparator();
        assert path != null;
        for (String pathItem : path.split(pathSeparator)) {
            File programFile = new File(pathItem, program);
            if (programFile.canExecute())
                return programFile;
        }
        return null;
    }

    String charset = "utf-8";

    String iocap(String cmdline)
            throws Exception {
        Process process = Processes.shellExec(cmdline);
        String out = Processes.iocap(process, charset);
        out = out.replaceAll("\r\n", "\n");
        return out;
    }

    @Test
    public void testIocapProcessString()
            throws Exception {
        assertEquals("hello\n", iocap("echo hello"));
        assertEquals("world \n", iocap("echo world 1>&2"));
    }

    @Test
    public void testMtpulse()
            throws Exception {
        File mtpulse = which("mtpulse");
        if (mtpulse != null) {
            System.out.println("mtpulse: " + mtpulse);
            assertEquals("123456789a", iocap("mtpulse C O1 X2 O3 X4 O5 X6 O7 X8 O9 Xa"));
        }
    }

}
