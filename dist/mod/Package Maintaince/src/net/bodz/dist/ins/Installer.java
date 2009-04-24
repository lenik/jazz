package net.bodz.dist.ins;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.gui.BasicGUI;
import net.bodz.swt.gui.pfl.WizardExitEvent;
import net.bodz.swt.gui.pfl.WizardExitListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

@DisplayName("boDz Product Installer")
// @Icon("icon.gif")
@PreferredSize(width = 500, height = 400)
@RcsKeywords(id = "$Id$")
@Version( { 1, 0 })
public class Installer extends BasicGUI {

    @Option(alias = "c")
    Class<?>         majorClass;

    private Project  project;
    private ISession session;

    // private InstallComposite installComposite;

    public Installer() {
    }

    public Installer(Project project) {
        this.project = project;
    }

    @Override
    protected void _boot() throws Throwable {
        if (majorClass != null) {
            if (project == null) {
                _Project projByClass = new _Project(majorClass);
                this.project = projByClass;
            }
        }
        if (project == null)
            throw new IllegalUsageError(PackNLS.getString("Installer.noConfig")); //$NON-NLS-1$
    }

    @Override
    protected void createInitialView(final Composite parent) throws UIException {
        ISession session = new Session(project, UI, L);
        setSession(session);
        InstallComposite installComposite = new InstallComposite(session, parent, SWT.NONE);
        installComposite.addExitListener(new WizardExitListener() {
            @Override
            public void wizardExit(WizardExitEvent e) {
                shell.dispose();
                System.out.println("Exit address: " + e.address); //$NON-NLS-1$
            }
        });
    }

    @Override
    protected String getTitle() {
        String text = project.getText();
        String version = project.getVersion();
        return text + PackNLS.getString("Installer.installer") + version; //$NON-NLS-1$
    }

    @Override
    protected String getBannerString() {
        ClassInfo info = _loadClassInfo();
        return PackNLS.getString("Installer.copyright") + info.getVersionString(); //$NON-NLS-1$
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

}
