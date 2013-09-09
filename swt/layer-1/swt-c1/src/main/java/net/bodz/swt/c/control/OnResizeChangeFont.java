package net.bodz.swt.c.control;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

public class OnResizeChangeFont
        extends ControlAdapter {

    private float origRows;

    private OnResizeChangeFont(float origRows) {
        this.origRows = origRows;
    }

    @Override
    public void controlResized(final ControlEvent e) {
        Control control = (Control) e.widget;
        // int width = control.getBounds().width;
        int height = control.getBounds().height;

        FontData fontData = control.getFont().getFontData()[0];
        float newFontHeight = Math.round(height / origRows);
        fontData.setHeight((int) newFontHeight);

        Font font = new Font(control.getDisplay(), fontData);
        control.setFont(font);
    }

    public static void apply(Control control) {
        Point origSize = control.getSize();
        FontData origFontData = control.getFont().getFontData()[0];
        int origFontHeight = origFontData.getHeight();

        // final float fontRatioX = (float) (origFontHeight) / origSize.x;
        float origRows = origSize.y / (float) (origFontHeight);

        control.addControlListener(new OnResizeChangeFont(origRows));
    }

}
