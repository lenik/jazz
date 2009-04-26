package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.io.Files;
import net.bodz.bas.snm.SJProject;
import net.bodz.dist.ins.BaseDirVariable;
import net.bodz.dist.ins.Component;

@DisplayName("Component Test Application")
@Doc("A test program for z.dist components")
@RcsKeywords(id = "$Id: ComponentTestApp.java 20 2009-04-22 12:33:53Z lenik $")
@Version( { 1, 2, 3 })
public class CTAProject extends SimpleProject {

    public static final String BASE_TEST = "BASE_TEST"; //$NON-NLS-1$

    public static final String BASE_A    = "BASE_A";   //$NON-NLS-1$
    public static final String BASE_B    = "BASE_B";   //$NON-NLS-1$
    public static final String BASE_C    = "BASE_C";   //$NON-NLS-1$

    public Section             classesSection;
    public Section             sourceSection;

    public CTAProject() {
        super(CTAProject.class);
        try {
            setup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setup() throws IOException {
        File testHome = Files.getTmpDir();

        define("BASE_A", new BaseDirVariable("Apples", new File(testHome, "apple"))); //$NON-NLS-1$
        define("BASE_B", new BaseDirVariable("Bees", new File(testHome, "bee"))); //$NON-NLS-1$
        define("BASE_C", new BaseDirVariable("Cakes", new File(testHome, "cake"))); //$NON-NLS-1$

        FileFilter filter = FileCopy.NoSVN;

        classesSection = new RequiredSection("bin", "Core Java Class Files"); //$NON-NLS-1$ //$NON-NLS-2$
        {
            File binDir = SJProject.getOutputBase(Component.class);
            FileFinder binfiles = new FileFinder(filter, binDir);
            FileCopy copyClassFiles = new FileCopy(BASE_PROGRAMS, binfiles);
            classesSection.add(copyClassFiles);
        }

        sourceSection = new DefaultSection("src", "Core Java Source Files"); //$NON-NLS-1$ //$NON-NLS-2$
        {
            File srcDir = SJProject.getSrcBase(Component.class);
            FileFinder srcfiles = new FileFinder(filter, srcDir);
            FileCopy copySrcFiles = new FileCopy(BASE_PROGRAMS, srcfiles);
            sourceSection.add(copySrcFiles);
        }
    }

}
