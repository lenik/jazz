package net.bodz.bas.t.specmap;

public class IPv4SpecMap<val_t>
        implements
            INetAddrSpecMap<val_t>,
            IIPv4SpecMap<val_t> {

    IntSpecNode<val_t> map;

    public IPv4SpecMap() {
        this.map = new IntSpecNode<>();
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
        IntSpecNode<val_t> node = getPrefixNode(address, prefix);
        if (node == null)
            return false;
        else
            return node.hasValue();
    }

    @Override
    public val_t getPrefix(int[] address, int prefix) {
        IntSpecNode<val_t> node = getPrefixNode(address, prefix);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    IntSpecNode<val_t> getPrefixNode(int[] address, int prefix) {
        return getOrAddPrefixNode(address, prefix, false);
    }

    IntSpecNode<val_t> getOrAddPrefixNode(int[] address, int prefix, boolean create) {
        IntSpecNode<val_t> node = map;
        int i = 0;
        while (prefix >= 8) {
            prefix -= 8;
            int component = address[i++];
            IntSpecNode<val_t> next = node.get(component);
            if (next == null)
                if (create)
                    next = node.getOrAdd(component, new IntSpecNode<>(node));
                else
                    return null;
            node = next;
        }
        if (prefix == 0) {
            IntSpecNode<val_t> next = node.getDefault();
            if (next == null)
                if (create)
                    next = node.getOrAddDefault(new IntSpecNode<>(node));
                else
                    return null;
            node = next;
        } else {
            int component = address[i];
            int varbits = 8 - prefix;
            int mask = ~((1 << varbits) - 1);
            int capacity = 1 << varbits;
            int start = component & mask;
            int end = start + capacity;
            IntSpecNode<val_t> next = node.getRange(start, end);
            if (next == null)
                if (create)
                    next = node.getOrAddRange(start, end, new IntSpecNode<>(node));
                else
                    return null;
            node = next;
        }
        return node;
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
        return getOrAddPrefixNode(address, prefix, true);
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
