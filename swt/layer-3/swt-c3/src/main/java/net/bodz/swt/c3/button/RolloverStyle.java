package net.bodz.swt.c3.button;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;

public class RolloverStyle {

    private Color foreground;
    private Color background;
    private Image backgroundImage;
    private Font font;

    public RolloverStyle(Control control) {
        foreground = control.getForeground();
        background = control.getBackground();
        backgroundImage = control.getBackgroundImage();
        font = control.getFont();
    }

    public RolloverStyle(Color foreground, Color background) {
        this(foreground, background, null, null);
    }

    public RolloverStyle(Color foreground, Image backgroundImage) {
        this(foreground, null, backgroundImage, null);
    }

    public RolloverStyle(Color foreground, Color background, Image backgroundImage, Font font) {
        this.foreground = foreground;
        this.background = background;
        this.backgroundImage = backgroundImage;
        this.font = font;
    }

    public void applyTo(Control control) {
        if (foreground != null)
            control.setForeground(foreground);
        if (background != null)
            control.setBackground(background);
        if (backgroundImage != null)
            control.setBackgroundImage(backgroundImage);
        if (font != null)
            control.setFont(font);
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
