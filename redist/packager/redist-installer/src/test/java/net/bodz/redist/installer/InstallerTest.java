package net.bodz.redist.installer;

import net.bodz.redist.installer.builtins.TestConfig;
import net.bodz.bas.c.reflect.Reflects;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.util.Job;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

import org.junit.Test;

public class InstallerTest {

    static {
        Reflects.setStatic(Job.class, "slowdown", 10);
    }

    @Test
    public void testFromDir()
            throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.getLogger().setLevel(LogLevel.DEBUG);
                session.addResFolder(new JavaioFile(TestConfig.outDir));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

    // @Test
    public void testFromZip()
            throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
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
