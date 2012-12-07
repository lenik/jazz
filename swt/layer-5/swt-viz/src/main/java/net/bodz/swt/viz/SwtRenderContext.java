package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFontType;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c3.dialog.SwtDialogs;
import net.bodz.swt.gui.api.ICommand;
import net.bodz.swt.gui.spec1.SwtColor;

public class SwtRenderContext {

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

        if (style.getVisibility() != null)
            control.setVisible(style.getVisibility() == VisibilityMode.visible);
        if (style.getEnabled() != null)
            control.setEnabled(style.getEnabled());

        if (style.getTooltip() != null)
            control.setToolTipText(style.getTooltip());

        IColor color = style.getColor();
        IColor backColor = style.getBackgroundColor();

        if (color != null)
            control.setForeground(SwtColor.convert(device, color.toRGB24()));
        if (backColor != null)
            control.setBackground(SwtColor.convert(device, backColor.toRGB24()));

        IFontType fontType = style.getFontType();
        if (fontType != null) {
            control.setFont(fontType);
        }

        if (style.getWidth() != null)
            control.setSize(style.getWidth());
    }

    public void addAction(ICommand action) {
    }

}