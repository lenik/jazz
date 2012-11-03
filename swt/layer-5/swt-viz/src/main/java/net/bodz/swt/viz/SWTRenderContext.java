package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c3.ia.SwtDialog;
import net.bodz.swt.gui.api.ICommand;

public class SWTRenderContext {

    public SwtDialog interact(Control active) {
        return new SwtDialog(active.getShell());
    }

    public void addEffects(Control control, IRefEntry_SWT<?> entry)
            throws RenderException {
        SwtStylesheet stylesheet = entry.getStylesheet();
        if (stylesheet == null)
            return;
        addEffects(control, stylesheet);
    }

    public void addEffects(Control control, SwtStylesheet hint) {
        Device device = control.getDisplay();
        if (hint.doc != null)
            control.setToolTipText(hint.doc);
        if (hint.visible != null)
            control.setVisible(hint.visible);
        if (hint.enabled != null)
            control.setEnabled(hint.enabled);
        if (hint.color != null)
            control.setForeground(new Color(device, hint.color));
        if (hint.backColor != null)
            control.setBackground(new Color(device, hint.backColor));
        Font font = hint.getFont(device);
        if (font != null)
            control.setFont(font);
        if (hint.preferredSize != null)
            control.setSize(hint.preferredSize);
    }

    public void addAction(ICommand action) {
    }

}
