package net.bodz.swt.gui.a;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.types.util.Types;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class A_gui extends net.bodz.bas.gui.a.A_gui {

    /**
     * @param device
     *            get the current device if <code>null</code>
     */
    public static Factory getFontFactory(net.bodz.bas.gui.a.Font fontAnnotation) {
        Class<? extends Factory> factoryClass = fontAnnotation.factory();
        if (factoryClass == Factory.class) {
            String name = fontAnnotation.name();
            int height = fontAnnotation.height();
            int style = fontAnnotation.style();
            FontData font = new FontData(name, height, style);
            return new Factory.Static(font);
        }
        try {
            return Types.getClassInstance(factoryClass);
        } catch (CreateException e) {
            throw new RuntimeException(e);
        }
    }

    public static Point parseSize(PreferredSize size) {
        if (size == null)
            return null;
        return new Point(size.width(), size.height());
    }

    static Map<String, RGB> colorNames;
    static {
        colorNames = new HashMap<String, RGB>();
        colorNames.put("black", new RGB(0, 0, 0));
        colorNames.put("white", new RGB(255, 255, 255));
        colorNames.put("red", new RGB(255, 0, 0));
        colorNames.put("green", new RGB(0, 255, 0));
        colorNames.put("blue", new RGB(0, 0, 255));
        colorNames.put("cyan", new RGB(0, 255, 255));
        colorNames.put("magenta", new RGB(255, 0, 255)); // pink
        colorNames.put("yellow", new RGB(255, 255, 0));
        colorNames.put("gray", new RGB(128, 128, 128));
    }

    public static RGB parseColor(String colorExp) {
        if (colorExp == null || colorExp.isEmpty())
            return null;
        int sharp = colorExp.indexOf('#');
        String name = sharp == -1 ? null : colorExp.substring(0, sharp);
        if (!name.isEmpty()) {
            RGB rgb = colorNames.get(name);
            if (rgb != null)
                return rgb;
        }
        // color name not exists, and the fail value isn't specified.
        if (sharp == -1)
            throw new IllegalAccessError("Bad color name: " + colorExp);
        String failval = colorExp.substring(sharp + 1);
        if (failval.length() != 6)
            throw new IllegalAccessError("color format: #rrggbb, given "
                    + failval);
        int rgb = Integer.parseInt(failval, 16);
        int blue = rgb & 0xff;
        rgb >>= 8;
        int green = rgb & 0xff;
        rgb >>= 8;
        int red = rgb & 0xff;
        // rgb >>= 8;
        // int alpha = rgb & 0xff;
        return new RGB(red, green, blue);
    }

}
