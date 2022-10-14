package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import net.bodz.bas.c.reflect.query.predicate.*;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.t.iterator.immed.AbstractMitable;

public abstract class FieldSelection
        extends AbstractMitable<Field> {

    protected int modifierMask;
    protected int modifierTest;

    protected IStringPredicate namePredicate;
    protected ITypePredicate typePredicate;

    static final int accessModifierMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;

    public FieldSelection withAccess(boolean includePublic, boolean includeProtected, boolean includePrivate) {
        modifierMask |= accessModifierMask;
        modifierTest &= ~accessModifierMask;
        if (includePublic)
            modifierTest |= Modifier.PUBLIC;
        if (includeProtected)
            modifierTest |= Modifier.PROTECTED;
        if (includePrivate)
            modifierTest |= Modifier.PRIVATE;
        return this;
    }

    public FieldSelection staticMode(boolean test) {
        modifierMask |= Modifier.STATIC;
        if (test)
            modifierTest |= Modifier.STATIC;
        return this;
    }

    public FieldSelection staticOnly() {
        return staticMode(true);
    }

    public FieldSelection finalMode(boolean test) {
        modifierMask |= Modifier.FINAL;
        if (test)
            modifierTest |= Modifier.FINAL;
        return this;
    }

    public FieldSelection finalOnly() {
        return finalMode(true);
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public FieldSelection nameEquals(String name) {
        namePredicate = new StringEquals(name, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public FieldSelection nameStartsWith(String namePrefix) {
        namePredicate = new StringStartsWith(namePrefix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>nameSuffix</code> is <code>null</code>.
     */
    public FieldSelection nameEndsWith(String nameSuffix) {
        namePredicate = new StringEndsWith(nameSuffix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>type</code> is <code>null</code>.
     */
    public FieldSelection derivedFrom(Class<?> type) {
        typePredicate = new TypeDerivedFrom(type, typePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>type</code> is <code>null</code>.
     */
    public FieldSelection ancestorOf(Class<?> type) {
        typePredicate = new TypeAncestorOf(type, typePredicate);
        return this;
    }

    public Field[] toArray() {
        return toList().toArray(new Field[0]);
    }

    public List<? extends Field> toList() {
        return Iterables.toList(this);
    }

    public boolean exists() {
        return iterator(true)._next() != null;
    }

}
