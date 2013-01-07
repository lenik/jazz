package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.c3.dialog.SwtDialogs;
import net.bodz.swt.gui.model.ICommand;
import net.bodz.swt.gui.style.SwtColors;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtDialogs(active.getShell());
    }

    public void addEffects(Control control, ISwtGUIRefEntry<?> entry)
            throws ViewBuilderException {
        MappedSwtVizStyleClass stylesheet = entry.getStyle();
        if (stylesheet == null)
            return;
        addEffects(control, stylesheet);
    }

    public void addEffects(Control control, MappedSwtVizStyleClass style) {
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
            control.setForeground(SwtColors.convert(device, color));
        if (backColor != null)
            control.setBackground(SwtColors.convert(device, backColor));

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
