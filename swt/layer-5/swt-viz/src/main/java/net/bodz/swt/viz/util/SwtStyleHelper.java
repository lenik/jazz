package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.gui.style.color.IColor_RGB24;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.gui.style.SwtFontDataMapper;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;

public class SwtStyleHelper {

    public void addEffects(Control control, ISwtGUIRefEntry<?> entry)
            throws ViewBuilderException {
        ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        if (styleDecl == null)
            return;
        addEffects(control, styleDecl);
    }

    public void addEffects(Control control, ISwtControlStyleDeclaration styleDecl) {
        Device device = control.getDisplay();

        if (styleDecl.getTooltip() != null)
            control.setToolTipText(styleDecl.getTooltip());

        if (styleDecl.getVisibility() != null)
            switch (styleDecl.getVisibility()) {
            case visible:
                control.setVisible(true);
                break;
            case hidden:
                control.setVisible(false);
                break;
            case collapse:
                break;
            default:
            }

        if (styleDecl.getEnabled() != null)
            control.setEnabled(styleDecl.getEnabled());

        IColor color = styleDecl.getColor();
        if (styleDecl.getColor() != null) {
            IColor_RGB24 rgb = color.toRGB24();
            control.setForeground(new Color(device, //
                    rgb.getRed8(), rgb.getGreen8(), rgb.getBlue8()));
        }

        color = styleDecl.getBackgroundColor();
        if (color != null) {
            IColor_RGB24 rgb = color.toRGB24();
            control.setBackground(new Color(device, //
                    rgb.getRed8(), rgb.getGreen8(), rgb.getBlue8()));
        }

        IFontType fontType = styleDecl.getFontType();
        if (fontType != null) {
            FontData fontData = SwtFontDataMapper.convert(fontType);
            Font font = new Font(device, fontData);
            control.setFont(font);
        }

        if (styleDecl.getWidth() != null)
            control.setSize(styleDecl.getWidth());
    }

}
