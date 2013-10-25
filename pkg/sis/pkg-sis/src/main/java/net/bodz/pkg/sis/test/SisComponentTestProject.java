package net.bodz.pkg.sis.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.java.io.PruneFileFilter;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.pkg.sis.BasicSisProject;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.SisVariable;
import net.bodz.pkg.sis.c.RequiredSection;
import net.bodz.pkg.sis.c.SisSection;
import net.bodz.pkg.sis.c.io.FileCopy;

/**
 * A test program for z.dist components.
 * 
 * @label Component Test Application
 */
@MainVersion({ 1, 2, 3 })
public class SisComponentTestProject
        extends BasicSisProject {

    private static final long serialVersionUID = 1L;

    public static final String BASE_TEST = "BASE_TEST";

    public static final String BASE_A = "BASE_A";
    public static final String BASE_B = "BASE_B";
    public static final String BASE_C = "BASE_C";

    /**
     * Core Java Class Files
     */
    public SisSection classesSection;

    /**
     * Core Java Source Files
     */
    public SisSection sourceSection;

    File testHome = TempFile.getTempRoot();

    /**
     * Apples
     */
    SisVariable appleVar = new SisVariable(new File(testHome, "apple"));

    /**
     * Bees
     */
    SisVariable beeVar = new SisVariable(new File(testHome, "bee"));

    /**
     * Cakes
     */
    SisVariable cakeVar = new SisVariable(new File(testHome, "cake"));

    public SisComponentTestProject() {
        super(SisComponentTestProject.class);
        try {
            setup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setup()
            throws IOException {

        setVariable("BASE_A", appleVar);
        setVariable("BASE_B", beeVar);
        setVariable("BASE_C", cakeVar);

        FileFilter filter = PruneFileFilter.EXCLUDE_SVN_DIR;

        MavenPomDir pkgPomDir = MavenPomDir.fromClass(ISisComponent.class);

        classesSection = new RequiredSection(this, "bin");
        {
            File binDir = pkgPomDir.find("target/bin");
            FileFinder binfiles = new FileFinder(filter, binDir);
            new FileCopy(classesSection, BASE_PROGRAMS, null, binfiles);
        }

        sourceSection = new SisSection(this, "src");
        {
            File srcDir = pkgPomDir.find("src/main/java");
            FileFinder srcfiles = new FileFinder(filter, srcDir);
            new FileCopy(sourceSection, BASE_PROGRAMS, null, srcfiles);
        }
    }

}
