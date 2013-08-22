package net.bodz.swt.viz;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;
import net.bodz.bas.gui.css3.property.VisibilityMode;
import net.bodz.bas.gui.style.FontType;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.swt.gui.style.SwtColors;
import net.bodz.swt.gui.style.SwtFontDataMapper;

public class SwtControlStyler {

    public void applyCommonStyle(Control control, IGUIElementStyleDeclaration styleDecl) {
        applyVisibility(control, styleDecl.getVisibility());
        applyEnabled(control, styleDecl.getEnabled());
        applyColor(control, styleDecl.getColor());
        applyBackColor(control, styleDecl.getBackgroundColor());
        applyFont(control, styleDecl.getFontType());
        applySize(control, styleDecl);
    }

    public void applyVisibility(Control control, VisibilityMode visibilityMode) {
        if (visibilityMode != null)
            control.setVisible(visibilityMode == VisibilityMode.visible);
    }

    public void applyEnabled(Control control, Boolean enabled) {
        if (enabled != null)
            control.setEnabled(enabled);
    }

    public void applyColor(Control control, IColor color) {
        if (color != null)
            control.setForeground(SwtColors.convert(color, control.getDisplay()));
    }

    public void applyBackColor(Control control, IColor backColor) {
        if (backColor != null)
            control.setBackground(SwtColors.convert(backColor, control.getDisplay()));
    }

    public void applyFont(Control control, ICss3StyleDeclaration styleDecl) {
        FontType fontType = new FontType(styleDecl);
        applyFont(control, fontType);

        // styleDecl.getTextTransform(); ...
    }

    public void applyFont(Control control, IFontType fontType) {
        if (fontType != null) {
            FontData fontData = SwtFontDataMapper.convert(fontType);
            Font font = new Font(control.getDisplay(), fontData);
            control.setFont(font);
        }
    }

    public void applySize(Control control, IGUIElementStyleDeclaration styleDecl) {
        styleDecl.getWidthType();
        styleDecl.getHeightType();

        Point size = control.getSize();
        styleDecl.getWidth();
        styleDecl.getHeight();
        // TODO set size...
    }

}
