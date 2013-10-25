package net.bodz.pkg.sis;

import java.io.File;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.pkg.os.win32.Win32Vars;
import net.bodz.pkg.sis.c.ChooseInstallProfile;
import net.bodz.pkg.sis.c.CustomComponents;
import net.bodz.pkg.sis.c.LicenseAgreement;
import net.bodz.pkg.sis.c.WelcomeLogo;

public class BasicSisProject
        extends AbstractSisProject
        implements II18nCapable {

    private static final long serialVersionUID = 1L;

    public static final String BASE_PROGRAMS = "BASE_PROGRAMS";

    /**
     * Where do you want to put the program files.
     * 
     * @label Program Files
     */
    SisVariable programsVar;

    public BasicSisProject(Class<?> artifactClass) {
        super(artifactClass);

        new WelcomeLogo(this);
        new LicenseAgreement(this);
        new ChooseInstallProfile(this);
        new CustomComponents(this);

        File programsDir = new File(PFILES_ROOT, getName() + "-" + getVersion());
        SisVariable programsVar = new SisVariable(programsDir);
        setVariable(BASE_PROGRAMS, programsVar);

        if (SystemInfo.isWin32())
            Win32Vars.populate(this);
    }

    protected static File PFILES_ROOT;
    static {
        String parent = "/usr/local";
        if (SystemInfo.isWin32()) {
            parent = System.getenv("ProgramFiles");
            if (parent == null)
                parent = "C:/Program Files";
        }
        PFILES_ROOT = FilePath.canoniOf(parent);
    }

}
