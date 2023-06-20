package net.bodz.bas.t.specmap;

import net.bodz.bas.fmt.json.IJsonOut;

public class SpecMapJsonDumper<key_t, val_t>
        implements
            ISpecMapVisitor<key_t, val_t> {

    IJsonOut out;

    public SpecMapJsonDumper(IJsonOut out) {
        this.out = out;
    }

    @Override
    public boolean beginTops() {
        out.key("tops: ");
        out.object();
        return true;
    }

    @Override
    public boolean visitTop(key_t key, val_t val) {
        out.key(String.valueOf(key));
        out.value(val);
        return true;
    }

    @Override
    public void endTops() {
        out.endObject();
    }

    @Override
    public boolean beginRanges() {
        out.key("ranges: ");
        out.object();
        return true;
    }

    @Override
    public boolean visitRange(IRange<? extends key_t> rangeKey, val_t val) {
        out.key(String.valueOf(rangeKey));
        out.value(val);
        return true;
    }

    @Override
    public void endRanges() {
        out.endObject();
    }

    @Override
    public void visitDefault(val_t value) {
        out.key("default");
        out.value(value);
    }

}
