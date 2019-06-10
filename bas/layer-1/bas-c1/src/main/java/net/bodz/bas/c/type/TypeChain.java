package net.bodz.bas.c.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.java.util.Collections;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.iterator.PrefetchedIterator;

public class TypeChain {

    public static Class<?>[] listSuperLast(Class<?> clazz) {
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (clazz != null) {
            list.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return list.toArray(new Class<?>[0]);
    }

    public static Class<?>[] listSuperFirst(Class<?> clazz) {
        Class<?>[] list = listSuperLast(clazz);
        Arrays.reverse(list);
        return list;
    }

    public static <T extends ITreeOut> void dumpTypeTree(Class<?> type, T out) {
        dumpTypeTree(type, out, false);
    }

    public static <T extends ITreeOut> void dumpTypeTree(Class<?> type, T out, boolean removeDupInterfaces) {
        if (type == null)
            throw new NullPointerException("type");
        if (out == null)
            throw new NullPointerException("out");
        out.println(TypeName.pretty(type));
        out.enter(); // getTextIndention().increaseIndentLevel();

        Class<?> superclass = type.getSuperclass();
        if (superclass != null)
            dumpTypeTree(superclass, out, removeDupInterfaces);

        Class<?>[] interfaces = type.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> iface = interfaces[i];
            out.println(TypeName.pretty(iface));
            dumpTypeTree(iface, out, removeDupInterfaces);
        }

        out.leave(); // decreaseIndentLevel();
    }

    public static Iterable<Class<?>> getImpliedTypes(Class<?> type) {
        return new ImpliedTypes(type);
    }

    public static Iterable<Class<?>> ancestors(final Class<?> type, Class<?>... deferred) {
        final List<Class<?>> deferredList;
        if (deferred != null && deferred.length != 0)
            deferredList = Collections.toList(deferred);
        else
            deferredList = null;

        return new Iterable<Class<?>>() {
            @Override
            public Iterator<Class<?>> iterator() {
                return new AncestorIterator(type, deferredList);
            }
        };
    }

}

class AncestorIterator
        extends PrefetchedIterator<Class<?>> {

    private Set<Class<?>> markSet;
    private List<Class<?>> queue;
    private List<Class<?>> newQueue;
    private List<Class<?>> deferredQueue;
    private int index;

    AncestorIterator(Class<?> start) {
        markSet = new HashSet<Class<?>>();
        queue = new ArrayList<Class<?>>();
        newQueue = new ArrayList<Class<?>>();

        queue.add(start);
    }

    AncestorIterator(Class<?> start, List<Class<?>> deferred) {
        this(start);

        if (deferred != null && !deferred.isEmpty()) {
            this.deferredQueue = deferred;
            for (Class<?> ex : deferred)
                markSet.add(ex);
        }
    }

    @Override
    protected Class<?> fetch() {
        if (queue.isEmpty()) {
            if (deferredQueue != null) {
                queue = deferredQueue;
                deferredQueue = null;
            } else
                return end();
        }

        Class<?> c = queue.get(index++);
        if (index == queue.size()) {
            for (Class<?> q : queue) {
                Class<?> qSuper = q.getSuperclass();
                if (qSuper != null && markSet.add(qSuper))
                    newQueue.add(qSuper);

                Class<?>[] qInterfaces = q.getInterfaces();
                for (int i = 0; i < qInterfaces.length; i++) {
                    Class<?> qInterface = qInterfaces[i];
                    if (markSet.add(qInterface))
                        newQueue.add(qInterface);
                }
            }
            List<Class<?>> tmp = queue;
            queue = newQueue;
            newQueue = tmp;
            newQueue.clear();
            index = 0;
        }
        return c;
    }

}

class ImpliedTypes
        implements Iterable<Class<?>> {

    private final Class<?> type;
    private Set<Class<?>> all;

    public ImpliedTypes(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public Iterator<Class<?>> iterator() {
        if (all == null) {
            all = new HashSet<Class<?>>();
            traverse(type);
        }
        return all.iterator();
    }

    void traverse(Class<?> type) {
        if (!all.add(type)) // already added
            return;

        if (type.isInterface())
            return;

        Class<?> superclass = type.getSuperclass();
        if (superclass != null)
            traverse(superclass);

        // getInterfaces == getDeclaredInterfaces
        for (Class<?> iface : type.getInterfaces()) {
            traverse(iface);
        }
    }

}
