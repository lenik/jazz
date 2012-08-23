package net.bodz.redist.installer;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.log.LogLevel;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.redist.installer.builtins.TestConfig;

public class ConsoleExecutorTest
        extends ConsoleExecutor {

    public ConsoleExecutorTest()
            throws IOException {
        super(new TestProject());
        logger.setLevel(LogLevel.INFO, 0);
    }

    @Test
    public void testPack()
            throws SessionException {
        IFile outdir = new JavaioFile(TestConfig.outDir);
        outdir.setAutoCreateParents(true);
        session.addResFolder(0, outdir);
        session.dump(Stdio.cout);
        pack();
    }

    @Test
    public void testInstall()
            throws SessionException {
        session.addResFolder(new JavaioFile(TestConfig.outDir));
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        install();
    }

    @Test
    public void testUninstall()
            throws SessionException {
        session.addResFolder(new JavaioFile(TestConfig.outDir));
        // uninstaller should get the installed base dir from system registry.
        session.setScheme(Schemes.MAXIMUM);
        TestConfig.setTestBaseDir(session);
        uninstall();
    }

}