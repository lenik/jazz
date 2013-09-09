package net.bodz.redist.installer.builtins;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.redist.installer.ConfigPage;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.IProject;
import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Installer;
import net.bodz.redist.installer.LogoImage;
import net.bodz.swt.c.canvas.Picture;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.resources.SWTResources;

public class LogoPage
        extends ConfigPage {

    public LogoPage(IComponent owner, ISession session) {
        super(owner, session);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        IProject project = session.getProject();
        iString name = project.getLabel();
        IVersion version = project.getVersion();
        return tr._("Welcome to install ") + name + " " + version;
    }

    @Override
    protected void createContents(Composite holder)
            throws PageException {
        holder.setLayout(new GridLayout());

        final Picture logoPicture = new Picture(holder, SWT.NONE, true);
        logoPicture.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ImageData logo = session.getProject().getLogo();
        if (logo == null) {
            LogoImage _logoImage = Installer.class.getAnnotation(LogoImage.class);
            String resource = _logoImage == null ? null : _logoImage.value();
            if (resource != null) {
                Image defaultLogo = SWTResources.getImageRes(Installer.class, resource);
                logoPicture.setImage(defaultLogo);
            }
        } else
            logoPicture.setImage(new Image(holder.getDisplay(), logo));
    }

}
