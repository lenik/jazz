package net.bodz.dist.ins;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.a.DisplayName;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.snm.SJProject;
import net.bodz.dist.ins.builtins.CTAProject;
import net.bodz.dist.ins.builtins.FileCopy;
import net.bodz.dist.ins.builtins.OptionalSection;
import net.bodz.dist.ins.builtins.Section;

@DisplayName("ABC Program")
public class TestProject extends CTAProject {

    public Section  testSection;

    public FileCopy copyTestClassesFiles;
    public FileCopy copyTestSrcFiles;

    public TestProject() throws IOException {
        // XXX - TestProject.class annotations?
        File parent = (File) get(BASE_PROGRAMS).getDefaultValue();
        Variable var = new BaseDirVariable(//
                "Test Files", // //$NON-NLS-1$
                "A place to put test related files.", // //$NON-NLS-1$
                new File(parent, "tests")); //$NON-NLS-1$
        define("BASE_TEST", var); //$NON-NLS-1$

        testSection = new OptionalSection("test", "Test source and classes"); //$NON-NLS-1$ //$NON-NLS-2$
        {
            File testBinDir = SJProject.getOutputBase(TestProject.class);
            File testSrcDir = SJProject.getSrcBase(TestProject.class);
            FileFinder testbin = new FileFinder(FileCopy.NoSVN, testBinDir);
            FileFinder testsrc = new FileFinder(FileCopy.NoSVN, testSrcDir);
            copyTestClassesFiles = new FileCopy(BASE_TEST, testbin);
            copyTestSrcFiles = new FileCopy(BASE_TEST, testsrc);
            testSection.add(copyTestClassesFiles);
            testSection.add(copyTestSrcFiles);
        }

        add(classesSection);
        add(sourceSection);
        add(testSection);
    }

}
