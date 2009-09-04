package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.ImageData;

public class PageMethod {

    private String    action;
    private ImageData image;
    private String    label;
    private String    toolTip;

    public PageMethod(String action) {
        this(action, null);
    }

    public PageMethod(Class<?> clazz) {
        this(clazz.getName(), null);
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

    private List<SelectionListener> selectionListeners;

    public void addSelectionListener(SelectionListener listener) {
        if (selectionListeners == null)
            selectionListeners = new ArrayList<SelectionListener>(1);
        selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        if (selectionListeners != null)
            selectionListeners.remove(listener);
    }

    final void fireSelection(SelectionEvent event) {
        if (selectionListeners != null)
            for (SelectionListener listener : selectionListeners)
                listener.widgetSelected(event);
    }

    @Override
    public String toString() {
        return label + "->" + action;
    }

}
