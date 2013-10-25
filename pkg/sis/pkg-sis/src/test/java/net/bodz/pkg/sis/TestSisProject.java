package net.bodz.pkg.sis;

import java.io.File;
import java.io.IOException;

import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.java.io.PruneFileFilter;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.pkg.sis.c.OptionalSection;
import net.bodz.pkg.sis.c.SisSection;
import net.bodz.pkg.sis.c.io.FileCopy;
import net.bodz.pkg.sis.test.SisComponentTestProject;

/**
 * @label ABC Program
 */
public class TestSisProject
        extends SisComponentTestProject {

    private static final long serialVersionUID = 1L;

    /**
     * A place to put test related files.
     * 
     * @label Test Files
     */
    SisVariable testDir;

    /**
     * Test source and classes
     */
    public SisSection testSection;

    public FileCopy copyTestClassesFiles;
    public FileCopy copyTestSrcFiles;

    public TestSisProject()
            throws IOException {
        // XXX - TestProject.class annotations?
        File parent = (File) getVariable(BASE_PROGRAMS).getInitValue();
        SisVariable testVar = new SisVariable(new File(parent, "tests"));
        setVariable("BASE_TEST", testVar);

        testSection = new OptionalSection(this, "test");
        {
            MavenPomDir testPomDir = MavenPomDir.fromClass(TestSisProject.class);

            File testBinDir = testPomDir.find("target/bin");
            File testSrcDir = testPomDir.find("src/main/java");

            copyTestClassesFiles = new FileCopy(testSection, BASE_TEST, null, //
                    new FileFinder(PruneFileFilter.EXCLUDE_SVN_DIR, testBinDir));
            copyTestSrcFiles = new FileCopy(testSection, BASE_TEST, null, //
                    new FileFinder(PruneFileFilter.EXCLUDE_SVN_DIR, testSrcDir));
        }
    }

}
