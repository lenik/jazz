package net.bodz.dist.ins;

import java.io.IOException;

import net.bodz.bas.util.LogTerm;

import org.junit.Test;

public class ConsoleExecutorTest extends ConsoleExecutor {

    public ConsoleExecutorTest() throws IOException {
        super(new TestProject());
        // L.setLevel(LogTerm.INFO);
        L.setLevel(LogTerm.DEBUG);
        session.setResFolder(TestConfig.outDir);
    }

    @Test
    public void testPack() throws SessionException {
        pack();
    }

    @Test
    public void testInstall() throws SessionException {
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        install();
    }

    @Test
    public void testUninstall() throws SessionException {
        // uninstaller should get the installed base dir from system registry.
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        uninstall();
    }

}
