package net.bodz.dist.ins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ZipResFolder;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util._Job;
import net.bodz.dist.ins.builtins.TestConfig;

import org.junit.Test;

public class InstallerTest {

    static {
        Reflects.setStatic(_Job.class, "slowdown", 10); //$NON-NLS-1$
    }

    @Test
    public void testFromDir() throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.getLogger().setLevel(LogTerm.DEBUG);
                session.addResFolder(new FileResFolder(TestConfig.outDir));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

    // @Test
    public void testFromZip() throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.getLogger().setLevel(LogTerm.DEBUG);
                session.addResFolder(new ZipResFolder(TestConfig.outDirZip));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

}
