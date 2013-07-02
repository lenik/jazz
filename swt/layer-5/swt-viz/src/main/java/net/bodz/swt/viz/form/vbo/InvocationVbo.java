package net.bodz.swt.viz.form.vbo;

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

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.potato.invoke.IInvocation;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IRefcomp;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.viz.ISwtControlStyleClass;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;
import net.bodz.swt.viz.grid.GridViewBuildStrategy;

public class InvocationVbo
        extends SwtViewBuilder<IInvocation> {

    protected final GridViewBuildStrategy gridStyle;

    public InvocationVbo(GridViewBuildStrategy gridStyle) {
        this.gridStyle = gridStyle;
    }

    @Override
    public Control buildView(final SwtRenderContext rc, IRefEntry<IInvocation> entry, ISwtControlStyleClass style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        if (!(entry instanceof IInvocationRefcomp))
            throw new IllegalArgumentException(tr._("R_CallObject.notInvocationRefcomp") + entry);

        final IInvocationRefcomp refcomp = (IRefcomp<?>) entry;
        InvocationDescriptor descriptor = refcomp.getDescriptor();
        BorderStyleMode borderStyle = style.getBorder().getStyle();

        final Composite comp = gridStyle.renderStruct(rc, refcomp, parent, styleInt);
        final Composite opbar = new Composite(comp, SWT.NONE);
        opbar.setLayoutData(new GridData(//
                SWT.FILL, SWT.CENTER, true, false, 3, 1));
        RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = rowLayout.marginRight = 0;
        rowLayout.marginTop = rowLayout.marginBottom = 0;
        opbar.setLayout(rowLayout);
        {
            final Button button = new Button(opbar, SWT.NONE);
            String label = style.getLabel();
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
