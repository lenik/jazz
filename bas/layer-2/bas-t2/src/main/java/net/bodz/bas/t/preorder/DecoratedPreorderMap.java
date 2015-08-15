package net.bodz.bas.t.preorder;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedPreorderMap<K, V>
        extends AbstractDecorator<IPreorderMap<K, V>>
        implements IPreorderMap<K, V> {

    private static final long serialVersionUID = 1L;

    public DecoratedPreorderMap(IPreorderMap<K, V> _orig) {
        super(_orig);
    }

    @Override
    public IPreorder<K> getPreorder() {
        return getWrapped().getPreorder();
    }

    @Override
    public java.util.Map.Entry<K, V> meetEntry(K key) {
        return getWrapped().meetEntry(key);
    }

    @Override
    public K meetKey(K key) {
        return getWrapped().meetKey(key);
    }

    @Override
    public V meet(K key) {
        return getWrapped().meet(key);
    }

    @Override
    public Iterable<java.util.Map.Entry<K, V>> joinEntries(K key) {
        return getWrapped().joinEntries(key);
    }

    @Override
    public Iterable<K> joinKeys(K key) {
        return getWrapped().joinKeys(key);
    }

    @Override
    public Iterable<V> join(K key) {
        return getWrapped().join(key);
    }

    @Override
    public Map<K, V> joinMap(K key) {
        return getWrapped().joinMap(key);
    }

    @Override
    public Set<K> joinKeySet(K key) {
        return getWrapped().joinKeySet(key);
    }

    @Override
    public Set<V> joinValueSet(K key) {
        return getWrapped().joinValueSet(key);
    }

    @Override
    public int size() {
        return getWrapped().size();
    }

    @Override
    public boolean isEmpty() {
        return getWrapped().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return getWrapped().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getWrapped().containsValue(value);
    }

    @Override
    public V get(Object key) {
        return getWrapped().get(key);
    }

    @Override
    public V put(K key, V value) {
        return getWrapped().put(key, value);
    }

    @Override
    public V remove(Object key) {
        return getWrapped().remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        getWrapped().putAll(m);
    }

    @Override
    public void clear() {
        getWrapped().clear();
    }

    @Override
    public Set<K> keySet() {
        return getWrapped().keySet();
    }

    @Override
    public Collection<V> values() {
        return getWrapped().values();
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return getWrapped().entrySet();
    }

}
