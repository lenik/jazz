package net.bodz.bas.ctx.scope.fast;

import java.util.*;

import org.apache.commons.collections15.collection.TransformedCollection;
import org.apache.commons.collections15.set.TransformedSet;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.StackUnderflowException;

public class FastScopeMap<K, V>
        extends AbstractMap<K, V> {

    private Map<K, VarNode> initmap;
    private Stack<FastScopeFrame> stack;
    private boolean removeLocalOnly = false;

    public FastScopeMap() {
        this(true);
    }

    public FastScopeMap(boolean order) {
        if (order)
            initmap = new TreeMap<>();
        else
            initmap = new HashMap<>();
        stack = new Stack<>();
        enter();
    }

    public synchronized void enter() {
        FastScopeFrame frame = new FastScopeFrame();
        stack.push(frame);
    }

    public synchronized void leave() {
        if (stack.size() <= 1)
            throw new StackUnderflowException();
        FastScopeFrame frame = stack.pop();
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
    public V put(K key, V value) {
        return define(key, value, false);
    }

    public V define(K key, V value) {
        return define(key, value, true);
    }

    @SuppressWarnings("unchecked")
    public synchronized V define(K key, V value, boolean createLocal) {
        VarNode init = init(key);
        VarNode head = init.next;
        FastScopeFrame frame = stack.peek();

        boolean create = head == null;
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
    public V remove(Object key) {
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
    public void clear() {
        initmap.clear();
        while (stack.size() > 1)
            stack.pop();
    }

    @Override
    public Set<K> keySet() {
        return initmap.keySet();
    }

    @Override
    public Collection<V> values() {
        return TransformedCollection.decorate(initmap.values(), new VarNode.ToV<V>());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return TransformedSet.decorate(initmap.entrySet(), new VarNode.ToVEntry<K, V>());
    }

}
