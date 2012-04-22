package net.bodz.art.installer;

import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.Version;
import net.bodz.bas.meta.info.DisplayName;
import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.gui.pfl.BadPathEvent;
import net.bodz.swt.gui.pfl.IBadPathListener;
import net.bodz.swt.reflect.BasicGUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @website http://www.bodz.net/products/SWT-Installer
 */
@DisplayName("boDz Product Installer")
// @Icon("icon.gif")
@LogoImage("logo2.jpg")
@PreferredSize(width = 500, height = 400)
@RcsKeywords(id = "$Id$")
@Version({ 1, 0 })
public class Installer
        extends BasicGUI {

    /**
     * @option -c
     */
    Class<?> majorClass;

    private IProject project;
    private ISession session;

    // private InstallComposite installComposite;

    public Installer() {
        this(null);
    }

    public Installer(IProject project) {
        this.project = project;
        this.L.setLevel(LogLevel.DETAIL);
    }

    @Override
    protected void _boot()
            throws Exception {
        if (majorClass != null) {
            if (project == null) {
                AbstractProject projByClass = new AbstractProject(majorClass);
                this.project = projByClass;
            }
        }
        if (project == null)
            throw new IllegalUsageError(PackNLS.getString("Installer.noConfig")); //$NON-NLS-1$
    }

    @Override
    protected void createInitialView(final Composite parent)
            throws UIException {
        ISession session = new Session(project, UI, L);
        setSession(session);
        InstallComposite installComposite = new InstallComposite(session, parent, SWT.NONE);
        installComposite.getPageFlow().addBadPathListener(new IBadPathListener() {
            @Override
            public void badPath(BadPathEvent e) {
                shell.dispose();
                System.out.println("Exit address: " + e.path); //$NON-NLS-1$
            }
        });
    }

    @Override
    protected String getTitle() {
        String text = project.getText();
        String version = project.getVersion();
        return text + PackNLS.getString("Installer.installer") + version; //$NON-NLS-1$
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

}
