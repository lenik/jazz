package net.bodz.bas.gui.style;

public class ImageUsage {

    public static final int NORMAL_SIZE = 0;
    public static final int SMALL_SIZE = 1;
    public static final int LARGE_SIZE = 2;

    private int size = NORMAL_SIZE;
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

    public static final ImageUsage NORMAL = normal(NORMAL_SIZE);
    public static final ImageUsage DISABLED = disabled(NORMAL_SIZE);
    public static final ImageUsage SELECTED = selected(NORMAL_SIZE);
    public static final ImageUsage HOVER = hover(NORMAL_SIZE);

}
