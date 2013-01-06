package net.bodz.swt.gui.style;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.color.IColor_RGB24;

public class SwtColors {

    public static Color convert(IColor color, Device device) {
        if (color instanceof SwtManagedColor)
            return ((SwtManagedColor) color).getSwtColor();
        else
            return create(device, color);
    }

    public static Color convert(SwtManagedColor managedColor) {
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
