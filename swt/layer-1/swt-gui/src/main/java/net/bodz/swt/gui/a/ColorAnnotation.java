package net.bodz.swt.gui.a;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.i18n.nls.II18nCapable;

public class ColorAnnotation
        implements II18nCapable {

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

    /**
     * [NAME]#AARRGGBB
     */
    public static RGB parseColor(String colorExp) {
        if (colorExp == null || colorExp.isEmpty())
            return null;
        int sharp = colorExp.indexOf('#');
        String name = sharp == -1 ? colorExp : colorExp.substring(0, sharp);
        if (!name.isEmpty()) {
            RGB rgb = colorNames.get(name);
            if (rgb != null)
                return rgb;
        }
        // color name not exists, and the fail value isn't specified.
        if (sharp == -1)
            throw new IllegalUsageError(tr._("Bad color name: ") + colorExp);
        String failval = colorExp.substring(sharp + 1);
        if (failval.length() != 6)
            throw new IllegalUsageError(tr._("color format: #rrggbb, given ") + failval);
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
