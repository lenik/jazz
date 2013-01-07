package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.gui.style.color.IColor_RGB24;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.MappedSwtVizStyleClass;

public class SwtStyleHelper {

    public void addEffects(Control control, ISwtGUIRefEntry<?> entry)
            throws ViewBuilderException {
        MappedSwtVizStyleClass stylesheet = entry.getStyle();
        if (stylesheet == null)
            return;
        addEffects(control, stylesheet);
    }

    public void addEffects(Control control, MappedSwtVizStyleClass style) {
        Device device = control.getDisplay();

        if (style.getTooltip() != null)
            control.setToolTipText(style.getTooltip());

        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
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

        if (style.getEnabled() != null)
            control.setEnabled(style.getEnabled());

        IColor color = style.getColor();
        if (style.getColor() != null) {
            IColor_RGB24 rgb = color.toRGB24();
            control.setForeground(new Color(device, //
                    rgb.getRed8(), rgb.getGreen8(), rgb.getBlue8()));
        }

        color = style.getBackgroundColor();
        if (color != null) {
            IColor_RGB24 rgb = color.toRGB24();
            control.setBackground(new Color(device, //
                    rgb.getRed8(), rgb.getGreen8(), rgb.getBlue8()));
        }

        IFontType font = style.getFont();
        if (font != null) {
            control.setFont(font);
        }

        if (style.getWidth() != null)
            control.setSize(style.getWidth());
    }

}
