package net.bodz.bas.c.java.io.capture;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import net.bodz.bas.io.BCharOut;

public class ManagedProcessTest {

    File mtpulse;

    public ManagedProcessTest() {
        String lapiota = System.getenv("LAPIOTA");
        if (lapiota != null) {
            File f = new File(lapiota, "bin/mtpulse.exe");
            if (f.exists())
                mtpulse = f;
        }
    }

    static String encoding = "utf-8";

    static class Cap
            extends _IOCallback {

        private String[] inputs;
        private BCharOut buf = new BCharOut();

        public Cap(String... inputs) {
            this.inputs = inputs;
        }

        @Override
        public void sendProc(OutputStream out)
                throws IOException {
            // CharOut cout = CharOuts.get(out);
            if (inputs != null)
                for (String input : inputs) {
                    // cout.println(input);
                    byte[] bytes = (input + "\n").getBytes();
                    out.write(bytes);
                }
            out.close();
        }

        @Override
        public void recvErr(byte[] b, int off, int len)
                throws IOException {
            String s = "!" + new String(b, off, len, encoding) + " ";
            buf.print(s);
            System.err.println(s);
        }

        @Override
        public void recvIn(byte[] b, int off, int len)
                throws IOException {
            String s = "-" + new String(b, off, len, encoding) + " ";
            buf.print(s);
            System.out.println(s);
        }

        @Override
        public String toString() {
            return buf.toString();
        }
    }

    static int INTERVAL = 40;

    void test(String cmd, String expected, int expectedExit, String... inputs)
            throws Exception {
        if (mtpulse == null) // skip
            return;
        Process process = Runtime.getRuntime().exec("mtpulse I" + INTERVAL + " " + cmd);
        Cap cap = new Cap(inputs);
        ManagedProcess mp = new ManagedProcess(cap);
        int retval = mp.takeOver(process);
        String cap_u = cap.toString().replace("\r", "");
        assertEquals(expected, cap_u);
        assertEquals(expectedExit, retval);
    }

    @Test
    public void testOutFirst()
            throws Exception {
        test("C a a a a a a a a a a a a a a a Xx", "-a -a -a -a -a -a -a -a -a -a -a -a -a -a -a !x ", 0);
    }

    @Test
    public void testErrFirst()
            throws Exception {
        test("C Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx Xx a", "!x !x !x !x !x !x !x !x !x !x !x !x !x !x !x -a ", 0);
    }

    @Test
    public void testCont()
            throws Exception {
        test("C a Xb Oc ?10", "-a !b -c ", 10);
    }

    @Test
    public void testNL()
            throws Exception {
        test("N a Xb Oc", "-a\n !b\n -c\n ", 0);
    }

    @Test
    public void testIn()
            throws Exception {
        test("C a E b E c", "-a -x\n -b -y\n -c ", 0, "x", "y", "z");
    }

    public static void main(String[] args)
            throws Throwable {
        Process catproc = Processes.shellExec("cat");
        ManagedProcess mp = new ManagedProcess();
        mp.takeOver(catproc);
    }

}
