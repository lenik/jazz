package net.bodz.swt.gui;

import java.lang.reflect.Field;

public class GUIField extends _GUIItem {

    private final Field field;

    public GUIField(Field field) {
        super(field.getName(), GUIHint.get(field));
        this.field = field;
    }

    @Override
    public Object get(Object obj) throws GUIAccessException {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new GUIAccessException(e);
        }
    }

    @Override
    public void set(Object obj, Object value) throws GUIAccessException {
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new GUIAccessException(e);
        }
    }

}
