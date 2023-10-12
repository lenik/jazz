package net.bodz.bas.doc.node.conv;

import net.bodz.bas.doc.node.FontStyleEnv;
import net.bodz.bas.doc.property.HorizAlignment;

public class MarkdownFn {

    public static String alignPattern(HorizAlignment alignment) {
        switch (alignment) {
        case LEFT:
        default:
            return "---";
        case RIGHT:
            return "--:";
        case CENTER:
            return ":-:";
        case FILL:
            return "---";
        }
    }

    /**
     * @param close
     *            <code>true</code> to reverse the chars order.
     */
    public static String mod(FontStyleEnv env, boolean close) {
        StringBuilder mod = new StringBuilder();
        if (env.isBold())
            mod.append("**");
        if (env.isItalic())
            mod.append("*");
        if (env.isStrikeline())
            mod.append("~~");
        if (env.isUnderline())
            mod.append("_");
        if (close)
            mod.reverse();
        return mod.toString();
    }

}
