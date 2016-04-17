package net.bodz.pkg.sis;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.ui.model.action.ActionPlayerConfig;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.zip.ZipVfsDevice;
import net.bodz.bas.vfs.impl.zip.ZipVfsDriver;
import net.bodz.pkg.sis.test.TestConfig;

public class ProjectInstallerTest
        extends Assert {

    static final Logger logger = LoggerFactory.getLogger(ProjectInstallerTest.class);

    static {
        ActionPlayerConfig.slowdown = 10;
    }

    @Test
    public void testFromDir()
            throws Throwable {
        logger.setLevel(LogLevel.DEBUG);

        TestSisProject project = new TestSisProject();
        project.setArchive(TestConfig.outDir);

        TestConfig.setTestBaseDir(project);

        SisProjectInstaller installer = new SisProjectInstaller(project);
        installer.run();
    }

    // @Test
    public void testFromZip()
            throws Throwable {
        TestSisProject project = new TestSisProject();

        IFile zipFile = TestConfig.outDirZip;
        String zipName = TestConfig.outDirZip.getName();
        ZipVfsDevice zipDevice = new ZipVfsDevice(ZipVfsDriver.getInstance(), zipFile, zipName);
        project.setArchive(zipDevice.getRootFile());

        TestConfig.setTestBaseDir(project);

        SisProjectInstaller installer = new SisProjectInstaller(project);

        installer.run();
    }

}
