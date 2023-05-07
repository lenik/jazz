package net.bodz.bas.t.specmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NetAddrSpecMap<val_t>
        extends AbstractSpecMapBase<int[], val_t>
        implements
            INetAddrSpecMap<val_t> {

    private IntSpecNode<val_t> map;
    private final int componentBits;

    public NetAddrSpecMap(int componentBits) {
        this.map = new IntSpecNode<>();
        this.componentBits = componentBits;
    }

    private static Integer[] box(int[] address) {
        Integer[] boxv = new Integer[address.length];
        for (int i = 0; i < address.length; i++)
            boxv[i] = address[i];
        return boxv;
    }

    public IntSpecNode<val_t> getNode(int[] address) {
        IntSpecNode<val_t> node = map;
        for (int i = 0; i < address.length; i++) {
            node = node.getTop(address[i]);
            if (node == null)
                return null;
        }
        return node;
    }

    public IntSpecNode<val_t> lazyCreateNode(int[] address) {
        IntSpecNode<val_t> node = map;
        for (int i = 0; i < address.length; i++) {
            int component = address[i];
            IntSpecNode<val_t> next = node.getTop(component);
            if (next == null) {
                next = new IntSpecNode<>(node);
                node.putTop(component, next);
            }
            node = next;
        }
        return node;
    }

    @Override
    public Set<int[]> topKeySet() {
        Set<int[]> addresses = new HashSet<>();
        if (map.hasValue())
            addresses.add(new int[0]);
        if (map.hasTop()) {
            List<Integer> prefix = new ArrayList<>();
            traverseTopPaths(map, prefix, addresses);
        }
        return addresses;
    }

    void traverseTopPaths(IntSpecNode<val_t> node, List<Integer> prefix, Collection<int[]> results) {
        Set<Integer> keySet = node.topKeySet();
        if (keySet.isEmpty())
            return;
        int last = prefix.size();
        prefix.add(null);
        for (Integer k : keySet) {
            IntSpecNode<val_t> child = node.getTop(k);
            if (child.isEmptyNode())
                // if (pruneEmptyNode)
                continue;

            prefix.set(last, k);
            if (child.hasValue()) {
                int[] address = new int[last + 1];
                for (int i = 0; i < address.length; i++)
                    address[i] = prefix.get(i);
                results.add(address);
            }
            if (child.hasTop())
                traverseTopPaths(child, prefix, results);
        }
        prefix.remove(last);
    }

    @Override
    public boolean hasTop() {
        // XXX
        return false;
    }

    @Override
    public boolean containsTop(int[] address) {
        IntSpecNode<val_t> node = getNode(address);
        if (node == null)
            return false;
        else
            return node.hasValue();
    }

    @Override
    public val_t getTop(int[] address) {
        IntSpecNode<val_t> node = getNode(address);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    @Override
    public final val_t find(int[] key) {
        IntSpecNode<val_t> node = map.find(box(key));
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    @Override
    public val_t putTop(int[] address, val_t value) {
        IntSpecNode<val_t> node = lazyCreateNode(address);
        return node.putValue(value);
    }

    @Override
    public boolean addTop(int[] address, val_t value) {
        IntSpecNode<val_t> node = lazyCreateNode(address);
        return node.addValue(value);
    }

    @Override
    public val_t removeTop(int[] address) {
        IntSpecNode<val_t> node = getNode(address);
        if (node == null)
            return null;
        else
            return node.removeValue();
    }

    @Override
    public void removeAllTops() {
        map.removeAllTops();
    }

    @Override
    public boolean containsPrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = getPrefixNode(address, prefix);
        if (node == null)
            return false;
        else
            return node.hasValue();
    }

    public IntSpecNode<val_t> getPrefixNode(int[] address, int prefix) {
        return resolvePrefixNode(address, prefix, false);
    }

    public IntSpecNode<val_t> lazyCreatePrefixNode(int[] address, int prefix) {
        return resolvePrefixNode(address, prefix, true);
    }

    IntSpecNode<val_t> resolvePrefixNode(int[] address, int prefix, boolean create) {
        IntSpecNode<val_t> node = map;
        IntSpecNode<val_t> next;
        for (int i = 0; i < address.length; i++, node = next) {
            int component = address[i];
            if (prefix >= componentBits) {
                prefix -= componentBits;
                next = node.getTop(component);
                if (next == null)
                    if (create)
                        next = node.getOrAddTop(component, new IntSpecNode<>(node));
                    else
                        return null;
            } else if (prefix > 0) {
                int varbits = componentBits - prefix;
                prefix = 0;
                int mask = ~((1 << varbits) - 1);
                int capacity = 1 << varbits;
                int start = component & mask;
                int end = start + capacity;
                next = node.getRange(start, end);
                if (next == null)
                    if (create)
                        next = node.getOrAddRange(start, end, new IntSpecNode<>(node));
                    else
                        return null;
            } else {
                next = node.getDefault();
                if (next == null)
                    if (create)
                        next = node.getOrAddDefault(new IntSpecNode<>(node));
                    else
                        return null;
            }
        }
        return node;
    }

    @Override
    public val_t getPrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = getPrefixNode(address, prefix);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    @Override
    public val_t putPrefix(int[] address, int prefix, val_t value) {
        IntSpecNode<val_t> node = lazyCreatePrefixNode(address, prefix);
        return node.putValue(value);
    }

    @Override
    public boolean addPrefix(int[] address, int prefix, val_t value) {
        IntSpecNode<val_t> node = lazyCreatePrefixNode(address, prefix);
        return node.addValue(value);
    }

    @Override
    public val_t removePrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = getPrefixNode(address, prefix);
        if (node == null)
            return null;
        else
            return node.removeValue();
    }

    @Override
    public void removeAllPrefixes() {
        map.removeAllRanges();
    }

    public void accept(ISpecNodeVisitor<? super IntSpecNode<val_t>, ? super Integer, ? super val_t> visitor) {
        map.accept(visitor);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
