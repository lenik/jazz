package net.bodz.bas.sys;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.CharOuts.Buffer;

import org.junit.Test;

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

    static class CapMP extends ManagedProcess {

        private String[] inputs;
        private Buffer   buf = new Buffer();

        public CapMP(String... inputs) {
            this.inputs = inputs;
        }

        @Override
        protected void recvErr(byte[] b, int off, int len) throws IOException {
            String s = "!" + new String(b, off, len, encoding) + " ";
            buf.print(s);
        }

        @Override
        protected void recvIn(byte[] b, int off, int len) throws IOException {
            String s = "-" + new String(b, off, len, encoding) + " ";
            buf.print(s);
        }

        @Override
        public synchronized int takeOver(Process process)
                throws InterruptedException {
            return super.takeOver(process);
        }

        @Override
        protected void start() {
            CharOut cout = CharOuts.get(out);
            if (inputs != null)
                for (String input : inputs) {
                    cout.println(input);
                }
        }

        @Override
        public String toString() {
            return buf.toString();
        }
    }

    void test(String cmd, String expected, int expectedExit, String... inputs)
            throws Exception {
        if (mtpulse == null) // skip
            return;
        Process process = Runtime.getRuntime().exec(cmd);
        ManagedProcess mp = new CapMP(inputs);
        int retval = mp.takeOver(process);
        String cap = mp.toString();
        assertEquals(expected, cap);
        assertEquals(expectedExit, retval);
    }

    @Test
    public void testCont() throws Exception {
        test("mtpulse C a Xb Oc ?10", "-a !b -c ", 10);
    }

    @Test
    public void testNL() throws Exception {
        test("mtpulse N a Xb Oc", "-a\n !b\n -c\n ", 0);
    }

    @Test
    public void testIn() throws Exception {
        test("mtpulse C a E b E c", "-a -x\n -b -y\n -c ", 0, "x", "y", "z");
    }

    public static void main(String[] args) throws Throwable {
        Process catproc = Processes.shellExec("cat");
        ManagedProcess mp = new ManagedProcess();
        mp.takeOver(catproc);
    }

}
