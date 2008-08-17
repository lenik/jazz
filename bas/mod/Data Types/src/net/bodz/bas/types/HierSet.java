package net.bodz.bas.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import net.bodz.bas.lang.Predicate;
import net.bodz.bas.lang.Predicate2s;

public class HierSet<E> extends TreeSet<E> {

    private static final long serialVersionUID = -3131717397860882935L;

    private Predicate2s<E>    relpred;

    public HierSet(Predicate2s<E> relations) {
        super();
        setRelations(relations);
    }

    public HierSet(Predicate2s<E> relations, Collection<? extends E> c) {
        super(c);
        setRelations(relations);
    }

    public HierSet(Predicate2s<E> relations, Comparator<? super E> comparator) {
        super(comparator);
        setRelations(relations);
    }

    public HierSet(Predicate2s<E> relations, SortedSet<E> s) {
        super(s);
        setRelations(relations);
    }

    public Predicate2s<E> getRelations() {
        return relpred;
    }

    public void setRelations(Predicate2s<E> relations) {
        assert relations != null : "null predicate";
        this.relpred = relations;
    }

    protected E nonexist() {
        // return null;
        throw new NoSuchElementException();
    }

    public boolean hasParent(E childIncl) {
        E floor = floor(childIncl);
        if (floor == null)
            return false;
        return relpred.eval(floor, childIncl);
    }

    public E getParent(E childIncl) {
        E floor = floor(childIncl);
        if (floor == null)
            return nonexist();
        if (relpred.eval(floor, childIncl))
            return floor;
        return nonexist();
    }

    public void findChildren(E parentIncl, Predicate<E> callback) {
        E ceiling = ceiling(parentIncl);
        while (ceiling != null) {
            if (!relpred.eval(parentIncl, ceiling))
                break;
            if (!callback.eval(ceiling))
                break;
            ceiling = higher(ceiling);
        }
    }

    public boolean hasChildren(E parentIncl) {
        final boolean[] boolv = new boolean[1];
        findChildren(parentIncl, new Predicate<E>() {
            @Override
            public boolean eval(E e) {
                boolv[0] = true;
                return false;
            }
        });
        return boolv[0];
    }

    public List<E> getChildren(E parentIncl) {
        final List<E> list = new ArrayList<E>();
        findChildren(parentIncl, new Predicate<E>() {
            @Override
            public boolean eval(E e) {
                list.add(e);
                return true;
            }
        });
        return list;
    }

}
