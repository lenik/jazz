package net.bodz.swt.viz.form.vbo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.potato.invoke.IInvocation;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.potato.ref.IRefcomp;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.grid.SwtGridViewBuilderFactory;

public class InvocationVbo
        extends AbstractSwtViewBuilder<IInvocation> {

    protected final SwtGridViewBuilderFactory gridStyle;

    public InvocationVbo(SwtGridViewBuilderFactory gridStyle) {
        this.gridStyle = gridStyle;
    }

    @Override
    public Widget buildView(Composite parent, ISwtGUIRefEntry<IInvocation> entry, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        final SwtRenderContext rc = options.get(SwtRenderContext.class);

        if (!(entry instanceof IInvocationRefcomp))
            throw new IllegalArgumentException(tr._("R_CallObject.notInvocationRefcomp") + entry);

        final IInvocationRefcomp refcomp = (IRefcomp<?>) entry;
        InvocationDescriptor descriptor = refcomp.getDescriptor();
        BorderStyleMode borderStyle = styleDecl.getBorder().getStyle();

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
            String label = entry.getLabel().toString();
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
