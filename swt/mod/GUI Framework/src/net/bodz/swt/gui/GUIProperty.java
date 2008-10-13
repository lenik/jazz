package net.bodz.swt.gui;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.Control;

public class GUIProperty extends _GUIItem {

    private Method readf;
    private Method writef;

    public GUIProperty(PropertyDescriptor property) {
        super(property.getName(), GUIHint.get(property));
        this.readf = property.getReadMethod();
        this.writef = property.getWriteMethod();
    }

    @Override
    public Object get(Object obj) throws GUIAccessException {
        if (readf == null)
            throw new UnsupportedOperationException("property " + name
                    + " is unreadable");
        try {
            return Control.invoke(readf, obj);
        } catch (IllegalArgumentException e) {
            throw new GUIAccessException(e);
        } catch (IllegalAccessException e) {
            throw new GUIAccessException(e);
        } catch (InvocationTargetException e) {
            throw new GUIAccessException(e);
        }
    }

    @Override
    public void set(Object obj, Object value) throws GUIAccessException {
        if (writef == null)
            throw new UnsupportedOperationException("property " + name
                    + " is unwritable");
        try {
            Control.invoke(writef, obj, value);
        } catch (IllegalArgumentException e) {
            throw new GUIAccessException(e);
        } catch (IllegalAccessException e) {
            throw new GUIAccessException(e);
        } catch (InvocationTargetException e) {
            throw new GUIAccessException(e);
        }
    }

}
