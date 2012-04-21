package net.bodz.swt.gui.pfl;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;

public class PageMethod
        extends SelectionAdapter {

    public static final int ABSOLUTE = 0;
    public static final int SIBLING = 1;
    public static final int CHILD = 2;

    private String action;

    private ImageData image;
    private String label;
    private String toolTip;

    public PageMethod(String action) {
        this(action, null);
    }

    public PageMethod(Class<?> clazz) {
        this(clazz.getName(), null);
    }

    public PageMethod(Class<?> clazz, String label) {
        this(clazz.getName(), label);
    }

    public PageMethod(String action, String label) {
        this(action, label, null);
    }

    public PageMethod(String action, String label, ImageData image) {
        this.action = action;
        if (label == null)
            label = "next";
        this.label = label;
        this.image = image;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ImageData getImage() {
        return image;
    }

    public void setImage(ImageData image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
    }

    @Override
    public String toString() {
        return label + "->" + action;
    }

}
