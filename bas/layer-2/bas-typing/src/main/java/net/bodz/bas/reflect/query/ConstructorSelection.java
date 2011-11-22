package net.bodz.bas.reflect.query;

import java.lang.reflect.Constructor;
import java.util.List;

import net.bodz.bas.util.iter.Iterables;

public abstract class ConstructorSelection
        extends _TypeVectorSelection<Constructor<?>, ConstructorSelection> {

    public Constructor<?>[] toArray() {
        return toList().toArray(new Constructor<?>[0]);
    }

    public List<? extends Constructor<?>> toList() {
        return Iterables.toList(this);
    }

}
