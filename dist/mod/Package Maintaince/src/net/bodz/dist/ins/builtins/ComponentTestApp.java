package net.bodz.dist.ins.builtins;

import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.ConsoleExecutor;
import net.bodz.dist.ins.SessionException;
import net.bodz.dist.ins.nls.PackNLS;

public class ComponentTestApp extends ConsoleExecutor {

    protected final CTAProject project;
    protected final Section    section;

    public ComponentTestApp() {
        super(new CTAProject(), LogTerms.console);
        this.project = (CTAProject) super.project;
        this.section = new DefaultSection("test", PackNLS.getString("ComponentTestApp.test.text"), //$NON-NLS-1$ //$NON-NLS-2$
                PackNLS.getString("ComponentTestApp.test.doc")); //$NON-NLS-1$
        this.project.add(section);
    }

    public void testPack() throws SessionException {
        pack();
    }

    public void testInstall() throws SessionException {
        install();
    }

    public void testUninstall() throws SessionException {
        uninstall();
    }

}
