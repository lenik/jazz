package net.bodz.dist.ins;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.io.PruneFileFilter;
import net.bodz.bas.snm.SJProject;
import net.bodz.dist.ins.builtins.DefaultSection;
import net.bodz.dist.ins.builtins.FileCopy;
import net.bodz.dist.ins.builtins.OptionalSection;
import net.bodz.dist.ins.builtins.RequiredSection;
import net.bodz.dist.ins.builtins.Section;
import net.bodz.dist.ins.builtins.SimpleProject;

@DisplayName("ABC Program")
@Doc("A test program for z.dist")
@RcsKeywords(id = "$Id: TestProject.java 10 2009-04-05 04:00:52Z Shecti $")
@Version( { 1, 2, 3 })
public class TestProject extends SimpleProject {

    static String   TEST = "test";

    public Section  classesSection;
    public Section  sourceSection;
    public Section  testSection;

    public FileCopy copyClassFiles;
    public FileCopy copySrcFiles;
    public FileCopy copyTestClassesFiles;
    public FileCopy copyTestSrcFiles;

    public TestProject() throws IOException {
        super(TestProject.class);

        BaseDir progdir = getBaseDir(PROGRAMS);
        File defaultTestDir = new File(progdir.getPreferred(), "tests");
        BaseDir testBase = new BaseDir(TEST, defaultTestDir, "Test Files",
                "A place to put test related files.");
        addBaseDir(testBase);

        FileFilter filter = new NoSVN();

        classesSection = new RequiredSection("classes", "Java Class Files");
        {
            File binDir = SJProject.getBase(IComponent.class);
            FileFinder binfiles = new FileFinder(filter, binDir);
            copyClassFiles = new FileCopy(PROGRAMS, binfiles);
            classesSection.add(copyClassFiles);
        }

        sourceSection = new DefaultSection("src", "Source Files");
        {
            File srcDir = SJProject.getSrcBase(IComponent.class);
            FileFinder srcfiles = new FileFinder(filter, srcDir);
            copySrcFiles = new FileCopy(PROGRAMS, srcfiles);
            sourceSection.add(copySrcFiles);
        }

        testSection = new OptionalSection("test", "Test source and classes");
        {
            File testBinDir = SJProject.getBase(TestProject.class);
            File testSrcDir = SJProject.getSrcBase(TestProject.class);

            FileFinder testbin = new FileFinder(filter, testBinDir);
            FileFinder testsrc = new FileFinder(filter, testSrcDir);
            copyTestClassesFiles = new FileCopy(TEST, testbin);
            copyTestSrcFiles = new FileCopy(TEST, testsrc);
            testSection.add(copyTestClassesFiles);
            testSection.add(copyTestSrcFiles);
        }

        add(classesSection);
        add(sourceSection);
        add(testSection);
    }

    class NoSVN implements PruneFileFilter {
        @Override
        public boolean accept(File pathname) {
            if (".svn".equals(pathname.getName().toLowerCase()))
                return false;
            return true;
        }
    }

}
