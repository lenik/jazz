package net.bodz.pkg.installer;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.pkg.installer.builtins.CTAProject;
import net.bodz.pkg.installer.builtins.FileCopy;
import net.bodz.pkg.installer.builtins.OptionalSection;
import net.bodz.pkg.installer.builtins.Section;

/**
 * @label ABC Program
 */
public class TestProject
        extends CTAProject {

    /**
     * Test source and classes
     */
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

        testSection = new OptionalSection("test");
        {
            MavenPomDir testPomDir = MavenPomDir.fromClass(TestProject.class);
            File testBinDir = testPomDir.find("target/bin");
            File testSrcDir = testPomDir.find("src/main/java");
            FileFinder testbin = new FileFinder(FileCopy.NoSVN, testBinDir);
            FileFinder testsrc = new FileFinder(FileCopy.NoSVN, testSrcDir);
            copyTestClassesFiles = new FileCopy(BASE_TEST, null, testbin);
            copyTestSrcFiles = new FileCopy(BASE_TEST, null, testsrc);
            testSection.add(copyTestClassesFiles);
            testSection.add(copyTestSrcFiles);
        }

        add(classesSection);
        add(sourceSection);
        add(testSection);
    }

}
