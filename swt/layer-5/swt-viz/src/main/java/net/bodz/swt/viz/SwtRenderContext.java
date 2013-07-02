package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.i18n.dom.iString;
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
        iString tooltip = entry.getDescription();
        if (tooltip != null)
            control.setToolTipText(tooltip.toPlainText());

        ISwtControlStyleClass style = entry.getStyle();
        if (style != null)
            addEffects(control, style);
    }

    public void addEffects(Control control, ISwtControlStyleClass style) {
        Device device = control.getDisplay();

        if (style.getVisibility() != null)
            control.setVisible(style.getVisibility() == VisibilityMode.visible);
        if (style.getEnabled() != null)
            control.setEnabled(style.getEnabled());

        IColor color = style.getColor();
        IColor backColor = style.getBackgroundColor();

        if (color != null)
            control.setForeground(SwtColors.convert(color, device));
        if (backColor != null)
            control.setBackground(SwtColors.convert(backColor, device));

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
