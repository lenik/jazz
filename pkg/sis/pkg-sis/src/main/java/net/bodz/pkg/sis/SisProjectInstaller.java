package net.bodz.pkg.sis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.program.model.IAppLifecycleListener;
import net.bodz.bas.ui.err.UiException;
import net.bodz.pkg.sisi.page.SisiNavigator;
import net.bodz.swt.c.pageflow.BadPathEvent;
import net.bodz.swt.c.pageflow.IBadPathListener;
import net.bodz.swt.program.BasicGUI;

/**
 * @label boDz Product Installer
 * @site http://www.bodz.net/products/SWT-Installer
 * @style width: 500; height: 400
 * @icon icon.gif
 * @logo logo2.jpg
 */
@MainVersion({ 1, 0 })
@RcsKeywords(id = "$Id$")
public class SisProjectInstaller
        extends BasicGUI
        implements
            IAppLifecycleListener<SisProjectInstaller> {

    static final Logger logger = LoggerFactory.getLogger(SisProjectInstaller.class);

    /**
     * @option -c
     */
    Class<?> majorClass;

    private ISisProject project;

    public SisProjectInstaller() {
        this(null);
    }

    public SisProjectInstaller(ISisProject project) {
        this.project = project;
    }

    @Override
    public void initDefaults(SisProjectInstaller app) {
        if (majorClass != null) {
            if (project == null) {
                AbstractSisProject projByClass = new AbstractSisProject(majorClass);
                this.project = projByClass;
            }
        }
        if (project == null)
            throw new IllegalUsageError(nls.tr("no config specified"));
    }

    @Override
    protected void createInitialView(final Composite parent)
            throws UiException {

        SisiNavigator navigator = new SisiNavigator(project, parent, SWT.NONE);

        navigator.getPageFlow().addBadPathListener(new IBadPathListener() {
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
        return label + nls.tr(" Installer ") + version;
    }

}
