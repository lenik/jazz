package net.bodz.redist.installer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.swt.c3.pageflow.BadPathEvent;
import net.bodz.swt.c3.pageflow.IBadPathListener;
import net.bodz.swt.program.BasicGUI;

/**
 * @name boDz Product Installer
 * @site http://www.bodz.net/products/SWT-Installer
 * @style width: 500; height: 400
 * @image logo2.jpg
 * @icon icon.gif
 */
@MainVersion({ 1, 0 })
@RcsKeywords(id = "$Id$")
public class Installer
        extends BasicGUI {

    static final Logger logger = LoggerFactory.getLogger(Installer.class);

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
        this.logger.setLevel(LogLevel.INFO);
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
        DomainString displayName = project.getDisplayName();
        IVersion version = project.getVersion();
        return displayName + tr._(" Installer ") + version;
    }

    public ISession getSession() {
        return session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

}
