package net.bodz.swt.gui.styles.grid;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.lang.err.ReflectException;
import net.bodz.swt.gui.GUIHint;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.gui.GUIStructs.GUICallMeta;
import net.bodz.swt.gui.GUIStructs.GUICallVar;

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

public class R_CallObject extends SWTRenderer {

    protected final SWTGridStyle gridStyle;

    public R_CallObject(SWTGridStyle gridStyle) {
        super(gridStyle);
        this.gridStyle = gridStyle;
    }

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        if (!(var instanceof GUICallVar))
            throw new IllegalArgumentException("not GUICallVar: " + var);
        final GUICallVar callVar = (GUICallVar) var;
        GUICallMeta meta = callVar.getMeta();
        GUIHint hint = meta.getHint();
        final Composite comp = gridStyle.renderStruct(callVar, parent, style);
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
                    } catch (ReflectException re) {
                        rc.interact(button).alert("Failed to invoke call", re);
                    }
                }
            });
        }
        return comp;
    }
}
