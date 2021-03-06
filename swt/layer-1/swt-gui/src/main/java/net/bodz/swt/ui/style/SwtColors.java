package net.bodz.swt.ui.style;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import net.bodz.bas.ui.style.IColor;
import net.bodz.bas.ui.style.color.IColor_RGB24;

public class SwtColors {

    public static Color convert(IColor color, Device device) {
        if (color instanceof SwtColor)
            return ((SwtColor) color).getSwtColor();
        else
            return create(device, color);
    }

    public static Color convert(SwtColor managedColor) {
        return managedColor.getSwtColor();
    }

    public static Color create(Device device, IColor color) {
        return create(device, color.toRGB24());
    }

    public static Color create(Device device, IColor_RGB24 color) {
        Color _color = new Color(device, color.getRed8(), color.getGreen8(), color.getBlue8());
        return _color;
    }

}
