package net.bodz.bas.ctx.util;

import java.util.*;
import java.util.function.Function;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.StackUnderflowException;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.pojo.Pair;

public abstract class AbstractFramedMap<K, V>
        extends AbstractMap<K, V>
        implements
            IFramedMap<K, V> {

    private final Map<K, VarNode> initmap;
    private final Stack<VarFrame> stack;
    private boolean removeLocalOnly = false;

    public AbstractFramedMap() {
        this(SortOrder.NONE);
    }

    public AbstractFramedMap(SortOrder order) {
        initmap = order.newMap();
        stack = new Stack<VarFrame>();
        enter();
    }

    @Override
    public synchronized void enter() {
        VarFrame frame = new VarFrame();
        stack.push(frame);
    }

    @Override
    public synchronized void leave() {
        if (stack.size() <= 1)
            throw new StackUnderflowException();
        VarFrame frame = stack.pop();
        VarNode head = frame.head;
        while (head != null) {
            head.remove();
            if (head.next == null) {
                VarNode init = head.prev;
                remove(init.data);
            }
            head = head.nextVar;
        }
    }

    @Override
    public int getDepth() {
        return stack.size() - 1;
    }

    synchronized VarNode init(K key) {
        VarNode init = initmap.get(key);
        if (init == null) {
            init = new VarNode(key);
            initmap.put(key, init);
        }
        return init;
    }

    @Override
    public int size() {
        return initmap.size();
    }

    @Override
    public boolean isEmpty() {
        return initmap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        VarNode init = initmap.get(key);
        return init != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (VarNode init : initmap.values()) {
            VarNode head = init.next;
            if (Nullables.equals(value, head.data))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        VarNode init = initmap.get(key);
        if (init == null)
            return null;

        VarNode head = init.next;
        assert head != null : "null head";

        @SuppressWarnings("unchecked")
        V val = (V) head.data;
        return val;
    }

    @Override
    public <T extends V> T _get(K key) {
        VarNode init = initmap.get(key);
        if (init == null)
            throw new NoSuchKeyException(String.valueOf(key));

        VarNode head = init.next;
        assert head != null : "null head";

        @SuppressWarnings("unchecked")
        T val = (T) head.data;
        return val;
    }

    @Override
    public <T extends V> T _get(K key, T defaultValue) {
        VarNode init = initmap.get(key);
        if (init == null)
            return defaultValue;

        VarNode head = init.next;
        assert head != null : "null head";

        @SuppressWarnings("unchecked")
        T val = (T) head.data;
        return val;
    }

    @Override
    public abstract V put(K key, V value);

    @Override
    public V define(K key, V value) {
        return define(key, value, true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized V define(K key, V value, boolean createLocal) {
        VarNode init = init(key);
        VarNode head = init.next;
        VarFrame frame = stack.peek();

        boolean create = head == null;
        if (head != null)
            if (createLocal && head.frame != frame)
                create = true;

        V old = null;
        if (create) {
            VarNode node = frame.alloc(value);
            init.next = node;
            node.prev = init;
            if (head != null) {
                node.next = head;
                head.prev = node;
            }
        } else {
            old = (V) head.data;
        }
        return old;
    }

    @Override
    public synchronized V remove(Object key) {
        VarNode init = initmap.get(key);
        if (init == null)
            return null;

        VarNode head = init.next;
        assert head != null;
        @SuppressWarnings("unchecked")
        V data = (V) head.data;

        if (removeLocalOnly)
            if (head.frame != stack.peek())
                return null;

        VarNode after = head.next;
        if (after != null) {
            after.prev = init;
            init.next = after;
            if (head.prevVar != null)
                head.prevVar.nextVar = head.nextVar;
            if (head.nextVar != null)
                head.nextVar.prevVar = head.prevVar;
            // head.detach();
        } else {
            initmap.remove(key);
        }
        return data;
    }

    @Override
    public synchronized void clear() {
        initmap.clear();
        while (stack.size() > 1)
            stack.pop();
    }

    @Override
    public Set<K> keySet() {
        return initmap.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        List<V> dataList = new ArrayList<V>(initmap.size());
        for (VarNode node : initmap.values()) {
            dataList.add((V) node.data);
        }
        return dataList;
    }

    /**
     * TODO Use transformed fn instead of hard copy.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (Entry<K, VarNode> entry : initmap.entrySet()) {
            VarNode node = entry.getValue();
            set.add(new Pair<K, V>(entry.getKey(), (V) node.data));
        }
        return set;
    }

}

class VarFrame {

    VarNode head;
    VarNode tail;

    public VarNode alloc(Object data) {
        VarNode node = new VarNode(data);
        node.frame = this;

        if (tail != null) {
            tail.nextVar = node;
            node.prevVar = tail;
        }

        tail = node;
        return node;
    }

}

class VarNode {

    VarNode prev; // var in child scope
    VarNode next; // var in parent scope

    VarFrame frame;
    VarNode prevVar;
    VarNode nextVar;

    Object data;

    public VarNode(Object data) {
        this.data = data;
    }

    public VarNode insert(Object data) {
        VarNode newPrev = frame.alloc(data);
        if (prev != null) {
            prev.next = newPrev;
            newPrev.prev = prev;
        }
        newPrev.next = this;
        this.prev = newPrev;
        return newPrev;
    }

    public void remove() {
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
    }

    public void detach() {
        prev = next = null;
        prevVar = nextVar = null;
    }

    public static class GetData<T>
            implements
                Function<VarNode, T> {

        @SuppressWarnings("unchecked")
        @Override
        public T apply(VarNode input) {
            return (T) input.data;
        }

    }

    public static class GetEntryOfData<K, T>
            implements
                Function<Map.Entry<K, VarNode>, Map.Entry<K, T>> {

        @SuppressWarnings("unchecked")
        @Override
        public Map.Entry<K, T> apply(Map.Entry<K, VarNode> input) {
            Pair<K, T> pair = new Pair<K, T>(input.getKey(), (T) input.getValue().data);
            return pair;
        }
    }

}
