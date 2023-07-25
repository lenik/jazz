package net.bodz.bas.bean.api;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

public interface IPropertyEditor {

    Object getValue();

    void setValue(Object value);

    boolean isPaintable();

    void paintValue(Graphics gfx, Rectangle box);

    String getJavaInitializationString();

    String getAsText();

    void setAsText(String text)
            throws IllegalArgumentException;

    String[] getTags();

    Component getCustomEditor();

    boolean supportsCustomEditor();

    void addPropertyChangeListener(IPropertyChangeListener listener);

    void removePropertyChangeListener(IPropertyChangeListener listener);

}