package net.bodz.bas.functors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang2.EvalException;
import net.bodz.bas.lang2.FunctorException;
import net.bodz.bas.nls.FPNLS;

public class Sequence<T> extends _Functor<T> implements Groupable<T>, List<IFunctor<T>> {

    private List<IFunctor<T>> list;
    private Map<Object, Integer> labels;

    public Sequence() {
        list = new ArrayList<IFunctor<T>>();
    }

    public Sequence(IFunctor<T> functor) {
        this();
        add(functor);
    }

    public Sequence(IFunctor<T>... functors) {
        this();
        for (IFunctor<T> f : functors)
            add(f);
    }

    @Override
    public T eval2() throws EvalException, Control {
        T last = null;
        if (list != null) {
            int size = size();
            for (int i = 0; i < size; i++) {
                IFunctor<T> functor = get(i);
                try {
                    last = functor.eval();
                } catch (ControlGoto controlGoto) {
                    Integer label = labels.get(controlGoto.getLabel());
                    if (label != null)
                        i = label.intValue();
                    else
                        throw controlGoto;
                }
            }
        }
        return last;
    }

    public IFunctor<?> reduce(IFunctor<?> outer) throws FunctorException {
        if (isReduced())
            return this;

        int size = list.size();
        if (size == 0)
            return null;

        int i = 0;
        boolean outgoes = false;
        while (i < size) {
            IFunctor<?> reduced = list.get(i).reduce();
            if (reduced == null) {
                remove(i);
                continue;
            }
            if (reduced instanceof ControlGoto)
                outgoes = true;
            i++;
        }

        setFlagBits(OUTGO, outgoes);

        if (!outgoes)
            labels.clear();
        else if (labels.size() != 0)
            return this;

        if (list.size() == 0)
            return null;

        if (list.size() == 1)
            return list.get(0);

        setFlagBits(REDUCED);
        return this;
    }

    public Groupable<T> concat(_Functor<T> functor) {
        list.add(functor);
        return this;
    }

    @Override
    public Sequence<T> label(Object labelKey) {
        if (labels == null)
            labels = new HashMap<Object, Integer>();
        if (labels.containsKey(labelKey))
            throw new RuntimeException(
                    FPNLS.getString("Sequence.label") + labelKey + FPNLS.getString("Sequence.duplicated")); //$NON-NLS-1$ //$NON-NLS-2$

        // .build( .concat(...).label(..).concat(...) )
        labels.put(labelKey, list.size());
        return this;
    }

    public IFunctor<T> labelAt(Object labelKey) {
        if (labels == null)
            return null;
        return get(labels.get(labelKey));
    }

    // List Implementation

    public boolean add(IFunctor<T> functor) {
        return list.add(functor);
    }

    public void add(int index, IFunctor<T> functor) {
        list.add(index, functor);
        for (Object key : labels.keySet()) {
            int val = labels.get(key);
            if (val >= index)
                labels.put(key, val + 1);
        }
    }

    public boolean addAll(int index, Collection<? extends IFunctor<T>> c) {
        int size = c.size();
        for (Object key : labels.keySet()) {
            int val = labels.get(key);
            if (val >= index)
                labels.put(key, val + size);
        }
        return list.addAll(index, c);
    }

    public void clear() {
        list.clear();
        for (Object key : labels.keySet()) {
            labels.put(key, 0);
        }
    }

    public IFunctor<T> remove(int index) {
        IFunctor<T> item = list.remove(index);
        for (Object key : labels.keySet()) {
            int val = labels.get(key);
            if (val >= index)
                labels.put(key, val - 1);
        }
        return item;
    }

    public boolean remove(Object o) {
        int size = list.size();
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (list.get(i) == null) {
                    remove(i);
                    return true;
                }
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(list.get(i))) {
                    remove(i);
                    return true;
                }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size();) {
            if (c.contains(get(i))) {
                remove(i);
                modified = true;
            } else {
                i++;
            }
        }
        return modified;
    }

    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size();) {
            if (!c.contains(get(i))) {
                remove(i);
                modified = true;
            } else {
                i++;
            }
        }
        return modified;
    }

    public boolean addAll(Collection<? extends IFunctor<T>> c) {
        return list.addAll(c);
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public IFunctor<T> get(int index) {
        return list.get(index);
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Iterator<IFunctor<T>> iterator() {
        return list.iterator();
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<IFunctor<T>> listIterator() {
        return list.listIterator();
    }

    public ListIterator<IFunctor<T>> listIterator(int index) {
        return list.listIterator(index);
    }

    public IFunctor<T> set(int index, IFunctor<T> element) {
        return list.set(index, element);
    }

    public int size() {
        return list.size();
    }

    public List<IFunctor<T>> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <E> E[] toArray(E[] a) {
        return list.toArray(a);
    }

}
