package net.bodz.pkg.sis;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.potato.ref.VarEntry;

public class SisVariable
        extends VarEntry<Object> {

    private static final long serialVersionUID = 1L;

    private Object initialValue;

    public SisVariable(Object initialValue) {
        this(initialValue.getClass(), initialValue);
    }

    public SisVariable(Class<?> valueType, Object initialValue) {
        super(valueType, initialValue);
        this.initialValue = initialValue;
    }

    public SisVariable(IElement element, Class<?> valueType, Object initialValue) {
        super(element, valueType, initialValue);
        this.initialValue = initialValue;
    }

    public Object getInitValue() {
        return initialValue;
    }

    public void setInitValue(Object initValue) {
        this.initialValue = initValue;
    }

    @Override
    public String toString() {
        Object value = get();
        return String.valueOf(value);
    }

}
