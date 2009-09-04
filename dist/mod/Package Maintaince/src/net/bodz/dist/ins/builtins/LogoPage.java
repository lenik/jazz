package net.bodz.dist.ins.builtins;

import net.bodz.bas.types.util.Ns;
import net.bodz.dist.ins.Component;
import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Installer;
import net.bodz.dist.ins.LogoImage;
import net.bodz.dist.ins.Project;
import net.bodz.dist.ins.nls.PackNLS;
import net.bodz.swt.controls.Picture;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class LogoPage extends ConfigPage {

    public LogoPage(Component owner, ISession session) {
        super(owner, session);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        Project project = session.getProject();
        String text = project.getText();
        String version = project.getVersion();
        return PackNLS.getString("LogoPage.title") + text + " " + version; //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    protected void createContents(Composite holder) throws PageException {
        holder.setLayout(new GridLayout());

        final Picture logoPicture = new Picture(holder, SWT.NONE, true);
        logoPicture.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ImageData logo = session.getProject().getLogo();
        if (logo == null) {
            String name = Ns._getValue(Installer.class, LogoImage.class);
            if (name != null) {
                Image defaultLogo = SWTResources.getImageRes(Installer.class, name);
                logoPicture.setImage(defaultLogo);
            }
        } else
            logoPicture.setImage(new Image(holder.getDisplay(), logo));
    }

}
