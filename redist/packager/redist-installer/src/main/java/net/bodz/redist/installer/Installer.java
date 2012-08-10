package net.bodz.redist.installer;

import static net.bodz.redist.installer.nls.PackNLS.PackNLS;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.c3.pageflow.BadPathEvent;
import net.bodz.swt.c3.pageflow.IBadPathListener;
import net.bodz.swt.program.BasicGUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @name boDz Product Installer
 * @website http://www.bodz.net/products/SWT-Installer
 */
// @Icon("icon.gif")
@LogoImage("logo2.jpg")
@MainVersion({ 1, 0 })
@PreferredSize(width = 500, height = 400)
@RcsKeywords(id = "$Id$")
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
            throw new IllegalUsageError(PackNLS.getString("Installer.noConfig"));
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
                System.out.println("Exit address: " + e.path);
            }
        });
    }

    @Override
    protected String getTitle() {
        String text = project.getText();
        String version = project.getVersion();
        return text + PackNLS.getString("Installer.installer") + version;
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

}
