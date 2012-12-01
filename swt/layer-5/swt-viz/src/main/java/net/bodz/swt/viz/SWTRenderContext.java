package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c3.dialog.SwtDialogs;
import net.bodz.swt.gui.api.ICommand;

public class SWTRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtDialogs(active.getShell());
    }

    public void addEffects(Control control, IRefEntry_SWT<?> entry)
            throws RenderException {
        SwtVizStyleClass stylesheet = entry.getStyle();
        if (stylesheet == null)
            return;
        addEffects(control, stylesheet);
    }

    public void addEffects(Control control, SwtVizStyleClass style) {
        Device device = control.getDisplay();
        if (style.getTooltip() != null)
            control.setToolTipText(style.getTooltip());
        if (style.getVisibility() != null)
            control.setVisible(style.getVisibility() == VisibilityMode.visible);
        if (style.getEnabled() != null)
            control.setEnabled(style.getEnabled());
        if (style.color != null)
            control.setForeground(new Color(device, style.color));
        if (style.backColor != null)
            control.setBackground(new Color(device, style.backColor));
        Font font = style.getFont(device);
        if (font != null)
            control.setFont(font);
        if (style.getWidth() != null)
            control.setSize(style.getWidth());
    }

    public void addAction(ICommand action) {
    }

}
