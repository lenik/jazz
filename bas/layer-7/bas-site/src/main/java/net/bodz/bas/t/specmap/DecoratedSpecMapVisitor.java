package net.bodz.bas.t.specmap;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedSpecMapVisitor<key_t, val_t>
        extends AbstractDecorator<ISpecMapVisitor<key_t, val_t>>
        implements
            ISpecMapVisitor<key_t, val_t> {

    private static final long serialVersionUID = 1L;

    public DecoratedSpecMapVisitor(ISpecMapVisitor<key_t, val_t> _orig, ISpecMapVisitor<key_t, val_t> orig) {
        super(_orig);
    }

    @Override
    public boolean beginTops() {
        return getWrapped().beginTops();
    }

    @Override
    public boolean visitTop(key_t key, val_t val) {
        return getWrapped().visitTop(key, val);
    }

    @Override
    public void endTops() {
        getWrapped().endTops();
    }

    @Override
    public boolean beginRanges() {
        return getWrapped().beginRanges();
    }

    @Override
    public boolean visitRange(IRange<? extends key_t> rangeKey, val_t val) {
        return getWrapped().visitRange(rangeKey, val);
    }

    @Override
    public void endRanges() {
        getWrapped().endRanges();
    }

    @Override
    public void visitDefault(val_t value) {
        getWrapped().visitDefault(value);
    }

    @Override
    public boolean beginValue(SpecLayer layer, Object layerKey) {
        return getWrapped().beginValue(layer, layerKey);
    }

    @Override
    public void visitValue(val_t value) {
        getWrapped().visitValue(value);
    }

    @Override
    public void endValue() {
        getWrapped().endValue();
    }

}
