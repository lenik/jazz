package net.bodz.swt.viz.builtin;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.potato.model.invoke.Invocation;
import net.bodz.bas.potato.model.invoke.InvocationDescriptor;
import net.bodz.bas.potato.model.invoke.InvocationRefcomp;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.GridVisualization;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.SwtStyleClass;

public class R_CallObject
        extends SWTRenderer {

    protected final GridVisualization gridStyle;

    public R_CallObject(GridVisualization gridStyle) {
        this.gridStyle = gridStyle;
    }

    @Override
    public Control render(final SWTRenderContext rc, IRefEntry<?> entry, SwtStyleClass stylesheet, Composite parent,
            int style)
            throws RenderException, SWTException {

        if (!(entry instanceof InvocationRefcomp))
            throw new IllegalArgumentException(tr._("R_CallObject.notInvocationRefcomp") + entry);

        final InvocationRefcomp refcomp = (InvocationRefcomp) entry;
        InvocationDescriptor descriptor = refcomp.getDescriptor();
        SwtStyleClass hint = descriptor.getStylesheet();

        final Composite comp = gridStyle.renderStruct(rc, refcomp, parent, style);
        final Composite opbar = new Composite(comp, SWT.NONE);
        opbar.setLayoutData(new GridData(//
                SWT.FILL, SWT.CENTER, true, false, 3, 1));
        RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = rowLayout.marginRight = 0;
        rowLayout.marginTop = rowLayout.marginBottom = 0;
        opbar.setLayout(rowLayout);
        {
            final Button button = new Button(opbar, SWT.NONE);
            String label = hint.getLabel();
            if (label == null)
                label = entry.getName();
            // hint.getIcon();
            button.setText(label);
            final Label retLabel = new Label(opbar, SWT.NONE);
            // = SWTGridStyle.this.render(retVar,opbar,SWT.NONE);
            final boolean isVoid = !descriptor.isNonVoid();
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    try {
                        Invocation invocation = refcomp.getInvocation();
                        Object retval = invocation.invoke();
                        if (!isVoid) {
                            retLabel.setText(String.valueOf(retval));
                            opbar.layout();
                        }
                    } catch (ReflectiveOperationException ex) {
                        String mesg = tr._("Failed to invoke call");
                        rc.getUserDialogs(button).alert(mesg, ex);
                    }
                }
            });
        }
        return comp;
    }
}
