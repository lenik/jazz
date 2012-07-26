package net.bodz.art.installer;

import net.bodz.art.installer.builtins.TestConfig;
import net.bodz.bas.c.reflect.Reflects;
import net.bodz.bas.log.LogLevel;

import org.junit.Test;

public class InstallerTest {

    static {
        Reflects.setStatic(_Job.class, "slowdown", 10); //$NON-NLS-1$
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
                session.addResFolder(new FileResFolder(TestConfig.outDir));
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
