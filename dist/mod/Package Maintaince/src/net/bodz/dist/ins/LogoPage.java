package net.bodz.dist.ins;

import net.bodz.bas.a.ClassInfo;
import net.bodz.swt.controls.Picture;
import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class LogoPage extends PageComposite {

    private IProject config;

    public LogoPage(IProject config, Composite parent, int style,
            ClassInfo installInfo) {
        super(parent, style);
        setLayout(new GridLayout());
        this.config = config;

        final Picture logoPicture = new Picture(this, SWT.NONE);
        logoPicture.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
                true));
        // logoPicture.setImage(null);
    }

    @Override
    public Image getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        String caption = config.getCaption();
        String version = config.getVersion();
        return "Welcome to install " + caption + " " + version;
    }

}
