package net.bodz.bas.bean.openbeans;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import net.bodz.bas.bean.api.IPropertyChangeListener;
import net.bodz.bas.bean.api.IPropertyEditor;

import com.googlecode.openbeans.PropertyChangeListener;
import com.googlecode.openbeans.PropertyEditor;

public class ObPropertyEditor
        implements
            IPropertyEditor {

    PropertyEditor pe;

    public ObPropertyEditor(PropertyEditor pe) {
        if (pe == null)
            throw new NullPointerException("pe");

        this.pe = pe;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {
        pe.paintValue(gfx, box);
    }

    @Override
    public void setAsText(String text)
            throws IllegalArgumentException {
        pe.setAsText(text);
    }

    @Override
    public String[] getTags() {
        return pe.getTags();
    }

    @Override
    public String getJavaInitializationString() {
        return pe.getJavaInitializationString();
    }

    @Override
    public String getAsText() {
        return pe.getAsText();
    }

    @Override
    public void setValue(Object value) {
        pe.setValue(value);
    }

    @Override
    public Object getValue() {
        return pe.getValue();
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pe.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pe.addPropertyChangeListener(listener);
    }

    @Override
    public Component getCustomEditor() {
        return pe.getCustomEditor();
    }

    @Override
    public boolean supportsCustomEditor() {
        return pe.supportsCustomEditor();
    }

    @Override
    public boolean isPaintable() {
        return pe.isPaintable();
    }

    @Override
    public void addPropertyChangeListener(IPropertyChangeListener listener) {
        pe.addPropertyChangeListener(ObPropertyChangeListener.convert(listener));
    }

    @Override
    public void removePropertyChangeListener(IPropertyChangeListener listener) {
        pe.removePropertyChangeListener(ObPropertyChangeListener.convert(listener));
    }

    public static ObPropertyEditor convert(PropertyEditor o) {
        if (o == null)
            return null;
        else
            return new ObPropertyEditor(o);
    }

    public static ObPropertyEditor[] convert(PropertyEditor[] src) {
        if (src == null)
            return null;
        ObPropertyEditor[] dst = new ObPropertyEditor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
