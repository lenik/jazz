package net.bodz.swt.viz;

import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.gui.style.StyleClass;

public class SwtStyleClass
        extends StyleClass {

    private static final long serialVersionUID = 1L;

    RGB color;
    RGB backColor;

    String menuItem;
    String toolItem;
    String viewId;

    public SwtStyleClass() {
        this.getColor().toRGB24();
        this.getBackgroundColor().toRGB24();
        switch (getFontStyle()) {
        case italic:
        case oblique:
        }
    }

}
