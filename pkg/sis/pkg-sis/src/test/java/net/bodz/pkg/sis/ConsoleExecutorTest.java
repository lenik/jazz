package net.bodz.pkg.sis;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.Stdio;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.ui.dialog.ConsoleDialogs;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.pkg.sis.test.TestConfig;
import net.bodz.pkg.sis.util.SisDumper;

public class ConsoleExecutorTest
        extends Assert {

    ISisProject project;
    ConsoleProgressMonitor monitor;
    Options options;

    public ConsoleExecutorTest()
            throws IOException {
        project = new TestSisProject();

        // XXX TODO
        // monitor.getLogger().setLevel(LogLevel.INFO, 0);
        options = new Options();
        options.addOption(IUserDialogs.class, ConsoleDialogs.stdout);
    }

    @Test
    public void testPack()
            throws IOException {
        project.setArchive(TestConfig.outDir);
        SisDumper.dump(Stdio.cout, project);
        project.pack(monitor, options);
    }

    @Test
    public void testInstall()
            throws SisException {
        project.setArchive(TestConfig.outDir);
        project.setInstallProfile(ISisInstallProfile.MAXIMUM);
        TestConfig.setTestBaseDir(project);
        project.install(monitor, options);
    }

    @Test
    public void testRemove()
            throws SisException {
        project.setArchive(TestConfig.outDir);
        // uninstaller should get the installed base dir from system registry.
        project.setInstallProfile(ISisInstallProfile.MAXIMUM);
        TestConfig.setTestBaseDir(project);
        project.remove(monitor, options);
    }

}
