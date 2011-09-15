package net.bodz.bas.reflect.query;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import net.bodz.bas.util.iter.AbstractMitablex;
import net.bodz.bas.util.iter.Iterables;

public abstract class FieldSelection
        extends AbstractMitablex<Field, RuntimeException> {

    protected int modifierMask;
    protected int modifierTest;

    protected INamePredicate namePredicate;
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

    public FieldSelection staticOnly() {
        modifierMask |= Modifier.STATIC;
        modifierTest |= Modifier.STATIC;
        return this;
    }

    public FieldSelection finalOnly() {
        modifierMask |= Modifier.FINAL;
        modifierTest |= Modifier.FINAL;
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public FieldSelection withName(String name) {
        namePredicate = new EqualsName(name, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>namePrefix</code> is <code>null</code>.
     */
    public FieldSelection startsWithName(String namePrefix) {
        namePredicate = new StartsWithName(namePrefix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>nameSuffix</code> is <code>null</code>.
     */
    public FieldSelection endsWithName(String nameSuffix) {
        namePredicate = new EndsWithName(nameSuffix, namePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>type</code> is <code>null</code>.
     */
    public FieldSelection of(Class<?> type) {
        typePredicate = new MinType(type, typePredicate);
        return this;
    }

    /**
     * @throws NullPointerException
     *             If <code>type</code> is <code>null</code>.
     */
    public FieldSelection superOf(Class<?> type) {
        typePredicate = new MaxType(type, typePredicate);
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
