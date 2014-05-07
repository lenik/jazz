package net.bodz.bas.ui.css3;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.meta.decl.ThreadUnsafe;
import net.bodz.bas.ui.style.color.RGB24Color;

public class WebColorParser {

    private String message;

    @ThreadUnsafe
    public RGB24Color parse(String s, RGB24Color fail) {
        message = null;

        RGB24Color color = WebColor.getColor(s);
        if (color != null)
            return color;

        if (s.startsWith("#")) {
            s = s.substring(1);
            if (!StringPred.isHexadecimal(s)) {
                message = "Bad hex color value: " + s;
                return fail;
            }

            switch (s.length()) {
            case 3:
                int rgb12 = Integer.parseInt(s, 16);
                int b8 = (rgb12 % 16) * 17;
                rgb12 >>= 4;
                int g8 = (rgb12 % 16) * 17;
                rgb12 >>= 4;
                int r8 = rgb12 * 17;
                return new RGB24Color(r8, g8, b8);

            case 6:
                int rgb24 = Integer.parseInt(s, 16);
                return RGB24Color.fromBE(rgb24);

            default:
                message = "Bad hex color format: " + s;
                return fail;
            }
        }

        message = "Illegal color value: " + s;
        return fail;
    }

    public String getMessage() {
        return message;
    }

}
