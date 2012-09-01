package net.bodz.redist.obfuz.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.swt.c3.pageflow.AbstractPage;
import net.bodz.swt.c3.pageflow.PageException;
import net.bodz.swt.c3.pageflow.PageMethod;
import net.bodz.swt.c3.pageflow.ServiceContext;

public class CantActivatePage
        extends AbstractPage {

    private final ABTSProvider abtsProvider;

    public CantActivatePage(ABTSProvider abtsProvider, PageMethod... methods) {
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
        return tr._("Can\'t activate this product");
    }

    @Override
    protected void createContents(Composite holder) {
        holder.setLayout(new GridLayout());

        final Label thankLabel = new Label(holder, SWT.NONE);
        thankLabel.setText(tr._("Activation canceled."));
    }

    @Override
    public TreePath service(ServiceContext context)
            throws PageException {
        ActivationByTargetString abts = abtsProvider.getABTS();
        if (abts != null)
            abts.clear();
        return null;
    }

}
