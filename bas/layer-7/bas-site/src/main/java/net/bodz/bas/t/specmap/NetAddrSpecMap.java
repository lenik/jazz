package net.bodz.bas.t.specmap;

import java.util.Map;

public class NetAddrSpecMap<val_t>
        extends DefaultSpecMap<int[], val_t>
        implements
            INetAddrSpecMap<val_t> {

    private IntSpecNode<val_t> map;
    private final int componentBits;

    public NetAddrSpecMap(int componentBits) {
        super((Map<int[], val_t>) null);
        this.map = new IntSpecNode<>();
        this.componentBits = componentBits;
    }

    private static Integer[] box(int[] address) {
        Integer[] boxv = new Integer[address.length];
        for (int i = 0; i < address.length; i++)
            boxv[i] = address[i];
        return boxv;
    }

    @Override
    public boolean containsKey(int[] address) {
        return map.find(box(address)) != null;
    }

    @Override
    public val_t find(int[] address) {
        IntSpecNode<val_t> node = map.find(box(address));
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    @Override
    public val_t put(int[] address, val_t value) {
        IntSpecNode<val_t> node = map.create(box(address));
        val_t last = node.getValue();
        node.setValue(value);
        return last;
    }

    @Override
    public boolean add(int[] address, val_t value) {
        IntSpecNode<val_t> node = map.create(box(address));
        if (node.getValue() != null)
            return false;
        node.setValue(value);
        return true;
    }

    @Override
    public val_t remove(int[] address) {
        IntSpecNode<val_t> node = map.find(box(address));
        if (node == null)
            return null;
        return node.removeValue();
    }

    @Override
    public void removeAllTops() {
        map.removeAllTops();
    }

    @Override
    public boolean containsPrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = resolvePrefix(address, prefix);
        if (node == null)
            return false;
        else
            return node.hasValue();
    }

    public IntSpecNode<val_t> resolvePrefix(int[] address, int prefix) {
        return resolvePrefix(address, prefix, false);
    }

    public IntSpecNode<val_t> resolvePrefix(int[] address, int prefix, boolean create) {
        IntSpecNode<val_t> node = map;
        IntSpecNode<val_t> next;
        for (int i = 0; i < address.length; i++, node = next) {
            int component = address[i];
            if (prefix >= componentBits) {
                prefix -= componentBits;
                next = node.get(component);
                if (next == null)
                    if (create)
                        next = node.getOrAdd(component, new IntSpecNode<>(node));
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
        IntSpecNode<val_t> node = resolvePrefix(address, prefix);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    @Override
    public val_t putPrefix(int[] address, int prefix, val_t value) {
        IntSpecNode<val_t> node = createPrefix(address, prefix);
        val_t last = node.getValue();
        node.setValue(value);
        return last;
    }

    @Override
    public boolean addPrefix(int[] address, int prefix, val_t value) {
        IntSpecNode<val_t> node = createPrefix(address, prefix);
        return node.addValue(value);
    }

    public IntSpecNode<val_t> createPrefix(int[] address, int prefix) {
        return resolvePrefix(address, prefix, true);
    }

    @Override
    public val_t removePrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = resolvePrefix(address, prefix);
        if (node == null)
            return null;
        else
            return node.removeValue();
    }

    @Override
    public void removeAllPrefixes() {
        map.removeAllRanges();
    }

    public val_t getValue() {
        return map.getValue();
    }

    public void setValue(val_t value) {
        map.setValue(value);
    }

    public void accept(ISpecNodeVisitor<? super IntSpecNode<val_t>, ? super Integer, ? super val_t> visitor) {
        map.accept(visitor);
    }

    @Override
    public String toString() {
        return map.toString();
    }

}
