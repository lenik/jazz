package net.bodz.bas.t.pojo;

import java.io.Serializable;
import java.util.Map.Entry;

import net.bodz.bas.c.object.Nullables;

public class Pair<K, V>
        implements
            IPair<K, V>,
            Entry<K, V>,
            Serializable {

    private static final long serialVersionUID = 1L;

    public K first;
    public V second;

    public Pair() {
    }

    public Pair(K first) {
        this.first = first;
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public K getFirst() {
        return first;
    }

    @Override
    public void setFirst(K first) {
        this.first = first;
    }

    @Override
    public V getSecond() {
        return second;
    }

    @Override
    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (object.getClass() != Pair.class)
            return false;
        return partialEquals((Pair<?, ?>) object);
    }

    public boolean partialEquals(Pair<?, ?> o) {
        if (!Nullables.equals(first, o.first))
            return false;
        if (!Nullables.equals(second, o.second))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0xb18e0b25;
        if (first != null)
            hash += first.hashCode();
        if (second != null)
            hash += second.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    // Map.Entry

    @Override
    public K getKey() {
        return first;
    }

    public K setKey(K key) {
        K old = first;
        this.first = key;
        return old;
    }

    @Override
    public V getValue() {
        return second;
    }

    @Override
    public V setValue(V value) {
        V old = second;
        second = value;
        return old;
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<K, V>(key, value);
    }

    public static <K, V> Pair<K, V> of(Entry<K, V> entry) {
        return new Pair<K, V>(entry.getKey(), entry.getValue());
    }

}
