package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.IRefEntry_SWT;
import net.bodz.swt.viz.SwtStylesheet;

public class SwtStyleHelper {

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

}
