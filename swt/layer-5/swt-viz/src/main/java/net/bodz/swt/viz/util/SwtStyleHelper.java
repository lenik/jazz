package net.bodz.swt.viz.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFontType;
import net.bodz.bas.gui.spec0.color.IColor_RGB24;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.IRefEntry_SWT;
import net.bodz.swt.viz.SwtVizStyleClass;

public class SwtStyleHelper {

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
            switch (style.getVisibility()) {
            case visible:
                control.setVisible(true);
                break;
            case hidden:
                control.setVisible(false);
                break;
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
