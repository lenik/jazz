package net.bodz.redist.installer.builtins;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import net.bodz.bas.c.java.io.FileFinder;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.snm.MavenProjectOrigin;
import net.bodz.redist.installer.BaseDirVariable;
import net.bodz.redist.installer.IComponent;

/**
 * A test program for z.dist components.
 * 
 * @label Component Test Application
 */
@RcsKeywords(id = "$Id: ComponentTestApp.java 20 2009-04-22 12:33:53Z lenik $")
@MainVersion({ 1, 2, 3 })
public class CTAProject
        extends SimpleProject {

    public static final String BASE_TEST = "BASE_TEST";

    public static final String BASE_A = "BASE_A";
    public static final String BASE_B = "BASE_B";
    public static final String BASE_C = "BASE_C";

    public Section classesSection;
    public Section sourceSection;

    public CTAProject() {
        super(CTAProject.class);
        try {
            setup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setup()
            throws IOException {
        File testHome = TempFile.getTmpDir();

        define("BASE_A",
                new BaseDirVariable(tr._("Apples"), new File(testHome, PackNLS
                        .getString("CTAProject.a"))));
        define("BASE_B",
                new BaseDirVariable(tr._("Bees"), new File(testHome, PackNLS
                        .getString("CTAProject.b"))));
        define("BASE_C",
                new BaseDirVariable(tr._("Cakes"), new File(testHome, PackNLS
                        .getString("CTAProject.c"))));

        FileFilter filter = FileCopy.NoSVN;

        MavenProjectOrigin redistPo = MavenProjectOrigin.fromClass(IComponent.class);

        classesSection = new RequiredSection("bin", "Core Java Class Files");
        {
            File binDir = redistPo.find("target/bin");
            FileFinder binfiles = new FileFinder(filter, binDir);
            FileCopy copyClassFiles = new FileCopy(BASE_PROGRAMS, binfiles);
            classesSection.add(copyClassFiles);
        }

        sourceSection = new DefaultSection("src", "Core Java Source Files");
        {
            File srcDir = redistPo.find("src/main/java");
            FileFinder srcfiles = new FileFinder(filter, srcDir);
            FileCopy copySrcFiles = new FileCopy(BASE_PROGRAMS, srcfiles);
            sourceSection.add(copySrcFiles);
        }
    }
}
