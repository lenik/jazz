package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.unit.std.LengthMeasure;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.c3.dialog.SwtDialogs;
import net.bodz.swt.gui.model.ICommand;
import net.bodz.swt.gui.style.SwtColors;
import net.bodz.swt.gui.style.SwtFontDataMapper;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtDialogs(active.getShell());
    }

    public void addEffects(Control control, ISwtGUIRefEntry<?> entry)
            throws ViewBuilderException {
        iString tooltip = entry.getDescription();
        if (tooltip != null)
            control.setToolTipText(tooltip.toPlainText());

        ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        if (styleDecl != null)
            addEffects(control, styleDecl);
    }

    public void addEffects(Control control, ISwtControlStyleDeclaration style) {
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
            FontData fontData = SwtFontDataMapper.convert(fontType);
            control.setFont(new Font(device, fontData));
        }

        Point size = control.getSize();
        style.getWidthType();
        style.getHeightType();
        LengthMeasure width = style.getWidth();
        LengthMeasure height = style.getHeight();

        // TODO set size...
    }

    public void addAction(ICommand action) {
    }

}
