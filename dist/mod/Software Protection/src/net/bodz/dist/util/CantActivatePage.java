package net.bodz.dist.util;

import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CantActivatePage extends PageComposite {

    public CantActivatePage(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout());

        final Label thankLabel = new Label(this, SWT.NONE);
        thankLabel.setText("Activation canceled.");
    }

    @Override
    protected Object getInitialState() {
        return "quit";
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return "Can't activate this product";
    }

}
