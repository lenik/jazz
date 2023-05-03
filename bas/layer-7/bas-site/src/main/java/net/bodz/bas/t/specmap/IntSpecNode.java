package net.bodz.bas.t.specmap;

public class IntSpecNode<val_t>
        extends RangeSpecNode<IntSpecNode<val_t>, Integer, val_t> {

    public IntSpecNode() {
        super();
    }

    public IntSpecNode(IntSpecNode<val_t> parent) {
        super(parent);
    }

    @Override
    public Class<Integer> getKeyType() {
        return Integer.class;
    }

    @Override
    public IntSpecNode<val_t> createNode() {
        return new IntSpecNode<>(this);
    }

    @Override
    public void removeAllRanges() {
        super.removeAllRanges();
        for (Integer top : topKeySet()) {
            IntSpecNode<val_t> val = getTop(top);
            val.removeAllRanges();
        }
    }

}
