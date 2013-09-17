package net.bodz.bas.gui.style;

public class ImageUsage {

    private int size;
    private int width;
    private int height;

    private boolean enabled = true;
    private boolean selected;
    private boolean hover;

    public ImageUsage(int size) {
        this.size = size;
    }

    public static ImageUsage normal(int size) {
        ImageUsage usage = new ImageUsage(size);
        return usage;
    }

    public static ImageUsage disabled(int size) {
        ImageUsage usage = new ImageUsage(size);
        usage.enabled = false;
        return usage;
    }

    public static ImageUsage selected(int size) {
        ImageUsage usage = new ImageUsage(size);
        usage.selected = true;
        return usage;
    }

    public static ImageUsage hover(int size) {
        ImageUsage usage = new ImageUsage(size);
        usage.hover = true;
        return usage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public static final ImageUsage NORMAL = normal(0);
    public static final ImageUsage DISABLED = disabled(0);
    public static final ImageUsage SELECTED = selected(0);
    public static final ImageUsage HOVER = hover(0);

}
