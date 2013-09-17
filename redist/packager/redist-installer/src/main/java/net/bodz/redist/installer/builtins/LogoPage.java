package net.bodz.redist.installer.builtins;

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
import net.bodz.mda.xjdoc.conv.ClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.redist.installer.ConfigPage;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.IProject;
import net.bodz.redist.installer.ISession;
import net.bodz.redist.installer.Installer;
import net.bodz.swt.c.canvas.Picture;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.gui.style.SwtImageMapper;

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
            ClassDoc classDoc = ClassDocLoader.load(Installer.class);
            IGUIElementStyleDeclaration styleDecl = classDoc.as(GUIElementDoc.class).getStyleClass();
            IImageData defaultLogo = styleDecl.getImage(ImageUsage.NORMAL);
            if (defaultLogo != null)
                logo = SwtImageMapper.convert(defaultLogo);
        }
        if (logo != null)
            logoPicture.setImage(new Image(holder.getDisplay(), logo));
    }

}
