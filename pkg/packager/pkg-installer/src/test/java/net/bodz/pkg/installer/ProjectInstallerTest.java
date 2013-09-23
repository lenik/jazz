package net.bodz.pkg.installer;

import org.junit.Test;

import net.bodz.bas.exec.job.JobConfig;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.installer.builtins.TestConfig;

public class ProjectInstallerTest {

    static {
        JobConfig.slowdown = 10;
    }

    @Test
    public void testFromDir()
            throws Throwable {
        TestProject project = new TestProject();
        ProjectInstaller installer = new ProjectInstaller(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.getLogger().setLevel(LogLevel.DEBUG);
                session.addResFolder(new PojfFile(TestConfig.outDir));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

    // @Test
    public void testFromZip()
            throws Throwable {
        TestProject project = new TestProject();
        ProjectInstaller installer = new ProjectInstaller(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.getLogger().setLevel(LogLevel.DEBUG);
                session.addResFolder(new ZipResFolder(TestConfig.outDirZip));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

}