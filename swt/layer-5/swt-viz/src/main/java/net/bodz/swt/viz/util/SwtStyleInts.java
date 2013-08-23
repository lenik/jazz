package net.bodz.swt.viz.util;

import org.eclipse.swt.SWT;

import net.bodz.bas.gui.css3.BorderBox;
import net.bodz.bas.gui.css3.ICss3StyleDeclaration;
import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.dom1.IGUIRefEntry;

public class SwtStyleInts {

    static final int DEFAULT_PPI = 72;

    public static int merge(int styleBits, ICss3StyleDeclaration styleDecl) {
        if (styleDecl == null)
            return styleBits;

        BorderBox borderBox = styleDecl.getBorder();
        while (borderBox != null) {
            BorderStyleMode borderStyle = borderBox.getStyle();
            if (borderStyle == BorderStyleMode.none)
                break;

            int borderWidth = borderBox.getWidth().pixels(DEFAULT_PPI);
            if (borderWidth == 0)
                break;

            // IColor_RGB24 borderColor = borderBox.getColor().toRGB24();
            styleBits |= SWT.BORDER;
        }

        return styleBits;
    }

    public static int merge(int styleBits, IGUIRefEntry<?> entry) {
        return merge(styleBits, entry.getStyle());
    }

}
