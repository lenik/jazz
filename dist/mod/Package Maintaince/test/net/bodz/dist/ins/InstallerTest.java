package net.bodz.dist.ins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ZipResFolder;
import net.bodz.bas.util.LogTerm;

import org.junit.Test;

public class InstallerTest {

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

    public void testFromJar() throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.addResFolder(new ZipResFolder(TestConfig.outJar));
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

}
