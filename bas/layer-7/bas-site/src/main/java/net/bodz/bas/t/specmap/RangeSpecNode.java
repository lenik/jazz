package net.bodz.bas.t.specmap;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.io.BCharOut;

public abstract class RangeSpecNode<node_t extends RangeSpecNode<node_t, key_t, val_t>, key_t extends Comparable<key_t>, val_t>
        extends DefaultRangeSpecMap<key_t, node_t>
        implements
            ISpecNode<node_t, key_t, val_t> {

    private node_t parent;

    private boolean hasValue;
    private val_t value;

    public RangeSpecNode() {
    }

    public RangeSpecNode(node_t parent) {
        this.parent = parent;
    }

    @Override
    public node_t getParent() {
        return parent;
    }

    @Override
    public boolean isEmptyNode() {
        getRange(null, null);
        return !(hasValue() || hasTop() || hasDefault());
    }

    @Override
    public boolean hasValue() {
        return hasValue;
    }

    @Override
    public val_t getValue() {
        if (hasValue)
            return value;
        else
            return null;
    }

    @Override
    public val_t putValue(val_t value) {
        val_t last = value;
        this.value = value;
        this.hasValue = true;
        return last;
    }

    @Override
    public boolean addValue(val_t value) {
        if (hasValue)
            return false;
        this.value = value;
        this.hasValue = true;
        return true;
    }

    @Override
    public val_t removeValue() {
        this.hasValue = false;
        val_t copy = this.value;
        this.value = null;
        return copy;
    }

    @Override
    public void accept(ISpecNodeVisitor<? super node_t, ? super key_t, ? super val_t> visitor) {
        @SuppressWarnings("unchecked")
        node_t _this = (node_t) this;
        if (visitor.isPruneEmptyNode())
            if (isEmptyNode())
                return;
        if (visitor.beginNode(_this)) {
            super.accept(visitor);
            if (hasValue)
                visitor.visitNodeValue(value);
            visitor.endNode(_this);
        }
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut(1000);
        SpecNodeDumper<node_t, key_t, val_t> dumper = new SpecNodeDumper<>(buf.indented());
        accept(dumper);
        return buf.toString();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        super.jsonOut(out, opts);
        if (hasValue)
            out.entry("value", value);
    }

}
