package net.bodz.swt.reflect;

import net.bodz.bas.ui.RenderException;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

public class SWTRenderContext {

    public DialogUI interact(Control active) {
        return new DialogUI(active.getShell());
    }

    public void addEffects(Control control, GUIVar<?> var) throws RenderException {
        GUIVarMeta meta = var.getMeta();
        GUIHint hint = meta.getHint();
        if (hint == null)
            return;
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

    public void addAction(IAction action) {
    }

}
