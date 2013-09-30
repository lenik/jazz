package net.bodz.pkg.installer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.pkg.installer.pageflow.InstallNavigator;
import net.bodz.swt.c.pageflow.BadPathEvent;
import net.bodz.swt.c.pageflow.IBadPathListener;
import net.bodz.swt.program.BasicGUI;

/**
 * @label boDz Product Installer
 * @site http://www.bodz.net/products/SWT-Installer
 * @style width: 500; height: 400
 * @image logo2.jpg
 * @icon icon.gif
 */
@MainVersion({ 1, 0 })
@RcsKeywords(id = "$Id$")
public class ProjectInstaller
        extends BasicGUI {

    static final Logger logger = LoggerFactory.getLogger(ProjectInstaller.class);

    /**
     * @option -c
     */
    Class<?> majorClass;

    private IProject project;
    private ISession session;

    // private InstallComposite installComposite;

    public ProjectInstaller() {
        this(null);
    }

    public ProjectInstaller(IProject project) {
        this.project = project;
    }

    @Override
    protected void reconfigure()
            throws Exception {
        if (majorClass != null) {
            if (project == null) {
                AbstractProject projByClass = new AbstractProject(majorClass);
                this.project = projByClass;
            }
        }
        if (project == null)
            throw new IllegalUsageError(tr._("no config specified"));
    }

    @Override
    protected void createInitialView(final Composite parent)
            throws GUIException {
        ISession session = new Session(project, userDialogs, logger);
        setSession(session);
        InstallNavigator installComposite = new InstallNavigator(session, parent, SWT.NONE);
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
        iString label = project.getLabel();
        IVersion version = project.getVersion();
        return label + tr._(" Installer ") + version;
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

}
