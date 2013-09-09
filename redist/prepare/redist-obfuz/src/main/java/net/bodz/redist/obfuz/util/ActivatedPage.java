package net.bodz.redist.obfuz.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.swt.c.pageflow.AbstractPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;

public class ActivatedPage
        extends AbstractPage {

    private final ABTSProvider abtsProvider;

    public ActivatedPage(ABTSProvider abtsProvider, PageMethod... methods) {
        if (abtsProvider == null)
            throw new NullPointerException("abtsProvider");
        this.abtsProvider = abtsProvider;
        setMethods(methods);
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return tr._("Successfully activated. ");
    }

    @Override
    protected void createContents(Composite parent) {
        parent.setLayout(new GridLayout());

        final Label thankLabel = new Label(parent, SWT.NONE);
        thankLabel.setText(tr._("Thank you for your registration!"));
    }

    @Override
    public PathEntries service(ServiceContext context)
            throws PageException {
        ActivationByTargetString abts = abtsProvider.getABTS();
        if (abts != null)
            abts.save();
        return null;
    }

}
