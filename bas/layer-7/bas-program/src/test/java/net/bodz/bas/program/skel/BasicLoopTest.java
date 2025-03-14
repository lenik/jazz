package net.bodz.bas.program.skel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class BasicLoopTest
        extends BasicLoop {

    static final Logger logger = LoggerFactory.getLogger(BasicLoopTest.class);

    public static int getPid() {
        try {
            return Integer.parseInt(Files.readSymbolicLink(Paths.get("/proc/self")).toString());
        } catch (NoSuchFileException e) {
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    protected void setUp()
            throws Exception {
        Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.err.println("Send interrupt to main thread");
                mainThread.interrupt();

//                int pid = getPid();
//                System.err.println("Send interrupt to process " + pid);
//                Runtime.getRuntime().exec("kill -SIGINT " + pid);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }, "kbdsend").start();
    }

    @Override
    protected boolean iterateMain() {
        System.out.println("[[ iterate main ]]");
        return true;
    }

    public static void main(String[] args)
            throws Exception {
        new BasicLoopTest().execute("-v", "-v", "-v", "-v");
    }

}
