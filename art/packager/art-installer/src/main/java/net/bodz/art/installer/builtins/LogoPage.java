package net.bodz.art.installer.builtins;

import static net.bodz.art.installer.nls.PackNLS.PackNLS;
import net.bodz.art.installer.ConfigPage;
import net.bodz.art.installer.IComponent;
import net.bodz.art.installer.IProject;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Installer;
import net.bodz.art.installer.LogoImage;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.Picture;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

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
        String text = project.getText();
        String version = project.getVersion();
        return PackNLS.getString("LogoPage.title") + text + " " + version;
    }

    @Override
    protected void createContents(Composite holder)
            throws PageException {
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
