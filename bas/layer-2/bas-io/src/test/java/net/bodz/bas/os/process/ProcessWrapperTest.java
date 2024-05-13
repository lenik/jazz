package net.bodz.bas.os.process;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.process.DbgOut;
import net.bodz.bas.io.process.MyProcessBuilder;
import net.bodz.bas.io.process.ProcessWrapper;

public class ProcessWrapperTest
        extends Assert {

    String captureOutput(String... cmdv)
            throws Exception {
        ProcessWrapper process = new MyProcessBuilder().command(cmdv).start();
        String out = process.waitForRunData().getOutputText();
        out = out.replaceAll("\r\n", "\n");
        return out;
    }

    @Test
    public void testIocapProcessString()
            throws Exception {
        assertEquals("hello\n", captureOutput("echo hello"));
        assertEquals("world\n", captureOutput("echo world 1>&2"));
    }

    @Test
    public void testMtpulse()
            throws Exception {
        File mtpulse = FilePath.which("mtpulse");
        if (mtpulse != null) {
            System.out.println("mtpulse: " + mtpulse);
            assertEquals("123456789a", captureOutput("mtpulse C O1 X2 O3 X4 O5 X6 O7 X8 O9 Xa"));
        }
    }

    public static void main(String[] args)
            throws IOException, InterruptedException {
        MyProcessBuilder builder = new MyProcessBuilder();
        builder.shell("for a in 1 2; do echo part $a; cat; done");
//        builder.command("cat");
        ProcessWrapper pw = builder.start();

        DbgOut dbgOut = new DbgOut(System.out, "2");
        DbgOut dbgErr = new DbgOut(System.err, "3");

        pw.supplyInput(System.in);
        pw.captureOutput((buf, off, len) -> dbgOut.write(buf, off, len));
        pw.captureError((buf, off, len) -> dbgErr.write(buf, off, len));

        pw.waitFor();
        System.out.println("all joined,quit");
    }

}
