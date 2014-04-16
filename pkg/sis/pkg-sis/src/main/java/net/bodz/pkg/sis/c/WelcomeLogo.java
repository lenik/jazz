package net.bodz.pkg.sis.c;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.gui.style.IImageData;
import net.bodz.bas.gui.style.ImageUsage;
import net.bodz.bas.gui.xjdoc.GUIElementDoc;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.mda.xjdoc.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.SisProjectInstaller;
import net.bodz.pkg.sisi.page.ConfigPage;
import net.bodz.swt.c.canvas.Picture;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.gui.style.SwtImageMapper;

public class WelcomeLogo
        extends ConfigComponent {

    private static final long serialVersionUID = 1L;

    public WelcomeLogo(ISisComponent parent) {
        super(parent, "welcome-logo");
    }

    @Override
    public ConfigPage createConfigPage() {
        return new WelcomeLogoPage(this);
    }

}

class WelcomeLogoPage
        extends ConfigPage {

    public WelcomeLogoPage(ISisComponent owner) {
        super(owner);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        ISisProject project = getProject();
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

        // ImageData logo = getProject().getLogo();
        IImageData logoData = getProject().getStyle().getImage(ImageUsage.normal(320));

        if (logoData == null) {
            ClassDoc classDoc = ClassDocLoader.load(SisProjectInstaller.class);
            IGUIElementStyleDeclaration styleDecl = classDoc.to(GUIElementDoc.class).getStyleClass();
            logoData = styleDecl.getImage(ImageUsage.NORMAL);
        }
        if (logoData != null) {
            Image logo = SwtImageMapper.convert(holder.getDisplay(), logoData);
            logoPicture.setImage(logo);
        }
    }

}
