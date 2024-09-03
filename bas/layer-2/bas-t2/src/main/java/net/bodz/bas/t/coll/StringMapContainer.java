package net.bodz.bas.t.coll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.UnexpectedException;

public class StringMapContainer<E>
        implements
            IContainer<E> {

    final Map<String, E> map;
    final Class<? extends E> valueType;
    private long nextId = 1;

    public StringMapContainer() {
        this(new LinkedHashMap<>());
    }

    public StringMapContainer(Map<String, E> map) {
        this.map = map;
        this.valueType = TypeParam.infer1(getClass(), StringMapContainer.class, 0);
    }

    public StringMapContainer(Class<? extends E> valueType) {
        this(new LinkedHashMap<>(), valueType);
    }

    public StringMapContainer(Map<String, E> map, Class<? extends E> valueType) {
        this.map = map;
        this.valueType = valueType;
    }

    @Override
    public ContainerType getType() {
        return ContainerType.STRING_MAP;
    }

    @Override
    public Class<? extends E> getElementType() {
        return valueType;
    }

    @Override
    public boolean isNamed() {
        return true;
    }

    @Override
    public Set<String> names() {
        return map.keySet();
    }

    @Override
    public boolean isNamePresent(String name) {
        return map.containsKey(name);
    }

    @Override
    public E get(String name) {
        return map.get(name);
    }

    @Override
    public E set(String name, E element) {
        return map.put(name, element);
    }

    @Override
    public boolean addNamed(E element, String name) {
        E prev = map.putIfAbsent(name, element);
        return prev == null;
    }

    @Override
    public E removeNamed(String name) {
        return map.remove(name);
    }

    @Override
    public boolean isIndexed() {
        return false;
    }

    @Override
    public boolean isIndexValid(int index) {
        return index >= 0 && index < map.size();
    }

    @Override
    public E get(int index) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        int i = 0;
        for (E el : map.values()) {
            if (i == index)
                return el;
            i++;
        }
        throw new UnexpectedException();
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (E el : map.values()) {
            if (Nullables.equals(el, o))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = 0;
        int lastMatch = -1;
        for (E el : map.values()) {
            if (Nullables.equals(el, o))
                lastMatch = i;
            i++;
        }
        return lastMatch;
    }

    @Override
    public void add(int index, E element) {
        add(element);
    }

    @Override
    public E set(int index, E element) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        int i = 0;
        Iterator<Entry<String, E>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, E> entry = iterator.next();
            if (i == index) {
                return entry.setValue(element);
            }
            i++;
        }
        throw new UnexpectedException();
    }

    @Override
    public E remove(int index) {
        if (! isIndexValid(index))
            throw new IndexOutOfBoundsException("" + index);
        int i = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E el = iterator.next();
            if (i == index) {
                iterator.remove();
                return el;
            }
            i++;
        }
        throw new UnexpectedException();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsValue(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return map.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return map.values().toArray(a);
    }

    String autoName(E e) {
        long id = nextId++;
        String name = Long.toHexString(id);
        return name;
    }

    @Override
    public boolean add(E e) {
        return map.putIfAbsent(autoName(e), e) == null;
    }

    @Override
    public boolean remove(Object o) {
        boolean changed = false;
        Iterator<Entry<String, E>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, E> entry = iterator.next();
            E value = entry.getValue();
            if (Nullables.equals(value, o)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return map.values().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E el : c)
            changed |= add(el);
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object el : c) {
            changed |= remove(el);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Iterator<Entry<String, E>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, E> entry = iterator.next();
            E value = entry.getValue();
            if (! c.contains(value)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        map.clear();
    }

    //

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public E get(Object key) {
        return map.get(key);
    }

    public E put(String key, E value) {
        return map.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends E> m) {
        map.putAll(m);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<E> values() {
        return map.values();
    }

    public Set<Entry<String, E>> entrySet() {
        return map.entrySet();
    }

    @Override
    public List<E> toList() {
        return new ArrayList<>(values());
    }

    @Override
    public Set<E> toSet() {
        return new LinkedHashSet<>(values());
    }

    @Override
    public Map<String, E> toMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        if (map == null)
            return "()";
        return map.toString();
    }

}
