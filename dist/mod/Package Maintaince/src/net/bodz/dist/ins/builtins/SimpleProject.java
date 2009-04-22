package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.BaseDir;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Project;
import net.bodz.dist.nls.PackNLS;

import org.eclipse.swt.widgets.Composite;

public class SimpleProject extends _Project {

    static class DisplayLogo extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent,
                int style) {
            return new LogoPage(session, parent, style);
        }

    }

    static class LicenseAgreement extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent,
                int style) {
            return new LicensePage(session, parent, style);
        }

    }

    static class ChooseScheme extends ConfigComponent {

        @Override
        public ConfigPage createConfig(ISession session, Composite parent,
                int style) {
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
        public ConfigPage createConfig(ISession session, Composite parent,
                int style) {
            return new CustomPage(session, parent, style);
        }

    }

    /** base dir */
    public static final String PROGRAMS = "programs"; //$NON-NLS-1$

    public SimpleProject(Class<?> clazz) {
        super(clazz);

        add(new DisplayLogo());
        add(new LicenseAgreement());
        add(new ChooseScheme());
        add(new CustomConfig());

        BaseDir programBase = new BaseDir(
                PROGRAMS,
                findProgramsDir(),
                "Program Files", PackNLS.getString("SimpleProject.doc.programs")); //$NON-NLS-1$ //$NON-NLS-2$
        addBaseDir(programBase);
    }

    File findProgramsDir() {
        String parent = "/usr/local"; //$NON-NLS-1$
        String name = getName();
        if (SystemInfo.isWin32()) {
            parent = System.getenv("ProgramFiles"); //$NON-NLS-1$
            if (parent == null)
                parent = "C:/Program Files"; //$NON-NLS-1$
        }
        File dir = new File(parent, name);
        return dir;
    }

}
