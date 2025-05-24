package net.bodz.bas.t.map;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.preorder.IPreorder;

public class TypeListPmap<E>
        extends ListMap<Class<?>, E>
        implements IListPmap<Class<?>, E> {

    final TypePoMap<List<E>> map;

    public TypeListPmap() {
        this(new TypePoMap<>());
    }

    public TypeListPmap(@NotNull TypePoMap<List<E>> map) {
        super(map);
        this.map = map;
    }

    @NotNull
    @Override
    public IPreorder<Class<?>> getPreorder() {
        return map.getPreorder();
    }

    @Override
    public Entry<Class<?>, List<E>> meetEntry(Class<?> key) {
        return map.meetEntry(key);
    }

    @Override
    public Class<?> meetKey(Class<?> key) {
        return map.meetKey(key);
    }

    @Override
    public List<E> meet(Class<?> key) {
        return map.meet(key);
    }

    @NotNull
    @Override
    public Iterable<Entry<Class<?>, List<E>>> joinEntries(Class<?> key) {
        return map.joinEntries(key);
    }

    @NotNull
    @Override
    public Iterable<Class<?>> joinKeys(Class<?> key) {
        return map.joinKeys(key);
    }

    @NotNull
    @Override
    public Iterable<List<E>> join(Class<?> key) {
        return map.join(key);
    }

    @NotNull
    @Override
    public Map<Class<?>, List<E>> joinMap(Class<?> key) {
        return map.joinMap(key);
    }

    @NotNull
    @Override
    public Set<Class<?>> joinKeySet(Class<?> key) {
        return map.joinKeySet(key);
    }

    @NotNull
    @Override
    public Set<List<E>> joinValueSet(Class<?> key) {
        return map.joinValueSet(key);
    }

}
