package net.bodz.dist.ins;

import org.junit.Test;

public class InstallerTest {

    @Test
    public void testFromDir() throws Throwable {
        TestProject project = new TestProject();
        Installer installer = new Installer(project) {
            @Override
            public void setSession(ISession session) {
                super.setSession(session);
                session.setResFolder(TestConfig.outDir);
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
                session.setResFolder(TestConfig.outJar);
                TestConfig.setTestBaseDir(session);
            }
        };
        installer.run();
    }

}
