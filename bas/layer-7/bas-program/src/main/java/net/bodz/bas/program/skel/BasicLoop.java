package net.bodz.bas.program.skel;

import java.util.concurrent.atomic.AtomicBoolean;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class BasicLoop
        extends BasicCLI {

    static final Logger logger = LoggerFactory.getLogger(BasicLoop.class);

    protected int interval = 1000;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        setUp();

        final Thread mainThread = Thread.currentThread();
        AtomicBoolean keepWorking = new AtomicBoolean(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("Oops! Trapped exit signal...");
            synchronized (mainThread) {
                mainThread.notify();
                keepWorking.set(false);
                try {
                    logger.debug("waiting for the main thread to finish...");
                    while (mainThread.isAlive())
                        mainThread.wait(1);
                    logger.debug("main thread ended.");
                } catch (InterruptedException e) {
                    logger.debug(e, "shtudown hook interrupted");
                }
            }
        }, "hook"));

        logger.debug("Starting... Ctrl-C to stop.");
        try {
            synchronized (mainThread) {
                int counter = 0;
                while (keepWorking.get()) {
                    logger.debug("iteration " + counter);
                    if (!iterateMain())
                        break;
                    logger.debug("    wait interval: " + interval);
                    mainThread.wait(interval);
                }
            }

            logger.debug("Tear down...");
            tearDown(); // This stuff takes time
            logger.debug("Tear down completed!");
        } catch (InterruptedException e) {
            logger.error(e, "main interrupted");
        }
        synchronized (mainThread) {
            mainThread.notify(); // Unlock the shutdown hook.
        }
        System.out.println("Main end");
    }

    protected abstract void setUp()
            throws Exception;

    protected abstract boolean iterateMain()
            throws Exception;

    protected void tearDown()
            throws Exception {
    }

}
