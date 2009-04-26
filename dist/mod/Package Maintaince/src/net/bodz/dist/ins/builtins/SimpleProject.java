package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.BaseDirVariable;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Variable;
import net.bodz.dist.ins._Project;
import net.bodz.dist.nls.PackNLS;

import org.eclipse.swt.widgets.Composite;

public class SimpleProject extends _Project {

    static class DisplayLogo extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent, int style) {
            return new LogoPage(session, parent, style);
        }

    }

    static class LicenseAgreement extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent, int style) {
            return new LicensePage(session, parent, style);
        }

    }

    static class ChooseScheme extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent, int style) {
            return new ChooseSchemePage(session, parent, style);
        }

    }

    static class CustomConfig extends ConfigComponent {

        @Override
        public boolean hasConfig() {
            // XXX - if scheme==custom return false..
            return super.hasConfig();
        }

        @Override
        public ConfigPage createConfig(ISession session, Composite parent, int style) {
            return new CustomPage(session, parent, style);
        }

    }

    public static final String BASE_PROGRAMS = "BASE_PROGRAMS"; //$NON-NLS-1$

    public SimpleProject(Class<?> clazz) {
        super(clazz);

        add(new DisplayLogo());
        add(new LicenseAgreement());
        add(new ChooseScheme());
        add(new CustomConfig());

        File programsDir = new File(PFILES_ROOT, getName() + "-" + getVersion()); //$NON-NLS-1$
        Variable programsVar = new BaseDirVariable(//
                PackNLS.getString("SimpleProject.programFiles"), // //$NON-NLS-1$
                PackNLS.getString("SimpleProject.doc.programs"), //$NON-NLS-1$
                programsDir);
        define(BASE_PROGRAMS, programsVar);

        if (SystemInfo.isWin32())
            Win32Vars.setup(this);
    }

    protected static File PFILES_ROOT;
    static {
        String parent = "/usr/local"; //$NON-NLS-1$
        if (SystemInfo.isWin32()) {
            parent = System.getenv("ProgramFiles"); //$NON-NLS-1$
            if (parent == null)
                parent = "C:/Program Files"; //$NON-NLS-1$
        }
        PFILES_ROOT = Files.canoniOf(parent);
    }

}
