package net.bodz.swt.reflect.styles.grid;

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

import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.SwtEntryMetadata;
import net.bodz.swt.reflect.GUIStructs.GUICallMeta;
import net.bodz.swt.reflect.GUIStructs.GUICallVar;
import net.bodz.swt.reflect.SwtEntry;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;

public class R_CallObject
        extends SWTRenderer {

    protected final SWTGridStrategy gridStyle;

    public R_CallObject(SWTGridStrategy gridStyle) {
        this.gridStyle = gridStyle;
    }

    @Override
    public Control render(final SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        if (!(var instanceof GUICallVar))
            throw new IllegalArgumentException(GUINLS.getString("R_CallObject.notGUICallVar") + var);
        final GUICallVar callVar = (GUICallVar) var;
        GUICallMeta meta = callVar.getMetadata();
        SwtEntryMetadata hint = meta.getHint();
        final Composite comp = gridStyle.renderStruct(rc, callVar, parent, style);
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
                label = meta.getName();
            // hint.getIcon();
            button.setText(label);
            final Label retLabel = new Label(opbar, SWT.NONE);
            // = SWTGridStyle.this.render(retVar,opbar,SWT.NONE);
            final boolean isVoid = meta.getReturnType() == void.class;
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    try {
                        Object retval = callVar.invoke();
                        if (!isVoid) {
                            retLabel.setText(String.valueOf(retval));
                            opbar.layout();
                        }
                    } catch (ReflectiveOperationException ex) {
                        String mesg = GUINLS.getString("R_CallObject.failedToCall");
                        rc.interact(button).alert(mesg, ex);
                    }
                }
            });
        }
        return comp;
    }
}
