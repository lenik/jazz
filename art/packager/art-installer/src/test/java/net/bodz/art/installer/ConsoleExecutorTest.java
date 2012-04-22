package net.bodz.art.installer;

import java.io.IOException;

import net.bodz.art.installer.builtins.TestConfig;

import org.junit.Test;


public class ConsoleExecutorTest extends ConsoleExecutor {

    public ConsoleExecutorTest() throws IOException {
        super(new TestProject());
        // L.setLevel(LogTerm.INFO);
        L.setLevel(LogTerm.DEBUG);
    }

    @Test
    public void testPack() throws SessionException {
        session.addResFolder(0, new FileResFolder(TestConfig.outDir, true));
        session.dump(CharOuts.stdout);
        pack();
    }

    @Test
    public void testInstall() throws SessionException {
        session.addResFolder(new FileResFolder(TestConfig.outDir, false));
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        install();
    }

    @Test
    public void testUninstall() throws SessionException {
        session.addResFolder(new FileResFolder(TestConfig.outDir, false));
        // uninstaller should get the installed base dir from system registry.
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        uninstall();
    }

}
