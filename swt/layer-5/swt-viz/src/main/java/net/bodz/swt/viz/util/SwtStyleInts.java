package net.bodz.swt.viz.util;

import org.eclipse.swt.SWT;

import net.bodz.bas.ui.css3.Border;
import net.bodz.bas.ui.css3.ICss3StyleDeclaration;
import net.bodz.bas.ui.css3.property.BorderStyleMode;
import net.bodz.bas.ui.dom1.IUiRef;

public class SwtStyleInts {

    static final int DEFAULT_PPI = 72;

    public static int merge(int styleBits, ICss3StyleDeclaration styleDecl) {
        if (styleDecl == null)
            return styleBits;

        Border border = styleDecl.getBorder();
        while (border != null) {
            BorderStyleMode borderStyle = border.getStyle();
            if (borderStyle == BorderStyleMode.none)
                break;

            int borderWidth = border.getWidth().pixels(DEFAULT_PPI);
            if (borderWidth == 0)
                break;

            // IColor_RGB24 borderColor = borderBox.getColor().toRGB24();
            styleBits |= SWT.BORDER;
        }

        return styleBits;
    }

    public static int merge(int styleBits, IUiRef<?> entry) {
        return merge(styleBits, entry.getStyle());
    }

}
