package net.bodz.bas.c.type.addor;

import java.lang.reflect.Array;

public class ArrayAddor
        implements IAddor {

    final Class<?> componentType;

    public ArrayAddor() {
        componentType = Object.class;
    }

    public ArrayAddor(Class<?> componentType) {
        if (componentType == null)
            throw new NullPointerException("componentType");
        this.componentType = componentType;
    }

    @Override
    public Object add(Object prev, Object item) {
        Object array;
        if (prev == null) {
            array = Array.newInstance(componentType, 1);
            Array.set(array, 0, item);
        } else {
            int len = Array.getLength(prev);
            // array = Arrays.copyOf((Object[]) prev, len + 1);

            Class<?> componentType = this.componentType;
            if (componentType == null)
                componentType = prev.getClass().getComponentType(); // this.componentType;

            array = Array.newInstance(componentType, len + 1);
            System.arraycopy(prev, 0, array, 0, len);
            Array.set(array, len, item);
        }
        return array;
    }

}
