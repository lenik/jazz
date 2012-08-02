package net.bodz.art.installer;

import java.io.File;
import java.io.IOException;

import net.bodz.art.installer.builtins.CTAProject;
import net.bodz.art.installer.builtins.FileCopy;
import net.bodz.art.installer.builtins.OptionalSection;
import net.bodz.art.installer.builtins.Section;
import net.bodz.bas.c.java.io.FileFinder;

/**
 * @name ABC Program
 */
public class TestProject
        extends CTAProject {

    public Section testSection;

    public FileCopy copyTestClassesFiles;
    public FileCopy copyTestSrcFiles;

    public TestProject()
            throws IOException {
        // XXX - TestProject.class annotations?
        File parent = (File) get(BASE_PROGRAMS).getDefaultValue();
        Variable var = new BaseDirVariable(//
                "Test Files", //
                "A place to put test related files.", //
                new File(parent, "tests"));
        define("BASE_TEST", var);

        testSection = new OptionalSection("test", "Test source and classes");
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
