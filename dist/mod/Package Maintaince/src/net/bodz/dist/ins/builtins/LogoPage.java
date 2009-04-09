package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.IProject;
import net.bodz.dist.ins.ISession;
import net.bodz.swt.controls.Picture;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class LogoPage extends ConfigPage {

    private ISession session;

    public LogoPage(ISession session, Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout());
        this.session = session;

        final Picture logoPicture = new Picture(this, SWT.NONE);
        logoPicture.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
                true));
        // logoPicture.setImage(null);
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
        return "Welcome to install " + text + " " + version;
    }

}
