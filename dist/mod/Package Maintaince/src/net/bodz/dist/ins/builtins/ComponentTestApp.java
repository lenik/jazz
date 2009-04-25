package net.bodz.dist.ins.builtins;

import net.bodz.bas.util.LogTerms;
import net.bodz.dist.ins.ConsoleExecutor;
import net.bodz.dist.ins.SessionException;

public class ComponentTestApp extends ConsoleExecutor {

    protected final CTAProject project;
    protected final Section    section;

    public ComponentTestApp() {
        super(new CTAProject(), LogTerms.console);
        this.project = (CTAProject) super.project;
        this.section = new DefaultSection("test", "Test Section",
                "Components under test should put them here");
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
