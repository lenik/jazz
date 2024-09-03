package net.bodz.bas.t.coll;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.c.type.TypeParam;

public abstract class OrderedContainer<E>
        implements
            IContainer<E> {

    protected final Class<? extends E> elementType;

    public OrderedContainer() {
        this.elementType = TypeParam.infer1(getClass(), OrderedContainer.class, 0);
    }

    public OrderedContainer(Class<? extends E> elementType) {
        if (elementType == null)
            throw new NullPointerException("elementType");
        this.elementType = elementType;
    }

    @Override
    public Class<? extends E> getElementType() {
        return elementType;
    }

    @Override
    public final Set<String> names() {
        Set<String> names = new LinkedHashSet<String>();
        int n = size();
        for (int i = 0; i < n; i++)
            names.add(String.valueOf(i));
        return names;
    }

    @Override
    public final boolean isNamePresent(String name) {
        if (StringPred.isDecimal(name)) {
            int index = Integer.parseInt(name);
            return isIndexValid(index);
        }
        return false;
    }

    @Override
    public final E get(String name) {
        if (StringPred.isDecimal(name)) {
            int index = Integer.parseInt(name);
            return get(index);
        }
        return null;
    }

    @Override
    public final E set(String name, E element) {
        if (name == null)
            throw new NullPointerException("name");
        if (StringPred.isDecimal(name)) {
            int index = Integer.parseInt(name);
            return set(index, element);
        }
        return null;
    }

    @Override
    public final boolean addNamed(E element, String name) {
        if (name == null)
            return add(element);
        if (StringPred.isDecimal(name)) {
            int index = Integer.parseInt(name);
            add(index, element);
            return true;
        }
        return add(element);
    }

    @Override
    public final E removeNamed(String name) {
        if (StringPred.isDecimal(name)) {
            int index = Integer.parseInt(name);
            if (isIndexValid(index))
                return remove(index);
        }
        return null;
    }

    @Override
    public Set<E> toSet() {
        return new LinkedHashSet<>(this);
    }

    @Override
    public Map<String, E> toMap() {
        Map<String, E> map = new LinkedHashMap<String, E>();
        int index = 0;
        for (E item : this) {
            String indexStr = String.valueOf(index++);
            map.put(indexStr, item);
        }
        return map;
    }

}
