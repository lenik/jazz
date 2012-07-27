package net.bodz.art.installer.builtins;

import java.io.File;
import java.nio.file.Files;

import net.bodz.art.installer.AbstractProject;
import net.bodz.art.installer.BaseDirVariable;
import net.bodz.art.installer.ConfigPage;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Variable;
import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.c.system.SystemInfo;

public class SimpleProject
        extends AbstractProject {

    static class DisplayLogo
            extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session) {
            return new LogoPage(this, session);
        }

    }

    static class LicenseAgreement
            extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session) {
            return new LicensePage(this, session);
        }

    }

    static class ChooseScheme
            extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session) {
            return new ChooseSchemePage(this, session);
        }

    }

    static class CustomConfig
            extends ConfigComponent {

        @Override
        public boolean hasConfig() {
            // XXX - if scheme==custom return false..
            return super.hasConfig();
        }

        @Override
        public ConfigPage createConfig(ISession session) {
            return new CustomPage(this, session);
        }

    }

    public static final String BASE_PROGRAMS = "BASE_PROGRAMS";

    public SimpleProject(Class<?> clazz) {
        super(clazz);

        add(new DisplayLogo());
        add(new LicenseAgreement());
        add(new ChooseScheme());
        add(new CustomConfig());

        File programsDir = new File(PFILES_ROOT, getName() + "-" + getVersion());
        Variable programsVar = new BaseDirVariable(//
                PackNLS.getString("SimpleProject.programFiles"), //
                PackNLS.getString("SimpleProject.doc.programs"), programsDir);
        define(BASE_PROGRAMS, programsVar);

        if (SystemInfo.isWin32())
            Win32Vars.setup(this);
    }

    protected static File PFILES_ROOT;
    static {
        String parent = "/usr/local";
        if (SystemInfo.isWin32()) {
            parent = System.getenv("ProgramFiles");
            if (parent == null)
                parent = "C:/Program Files";
        }
        PFILES_ROOT = Files.canoniOf(parent);
    }

}
