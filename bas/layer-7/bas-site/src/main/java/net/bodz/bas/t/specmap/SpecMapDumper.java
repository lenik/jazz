package net.bodz.bas.t.specmap;

import net.bodz.bas.io.ITreeOut;

public class SpecMapDumper<key_t, val_t>
        implements
            ISpecMapVisitor<key_t, val_t> {

    ITreeOut out;

    public SpecMapDumper(ITreeOut out) {
        this.out = out;
    }

    @Override
    public boolean beginTops() {
//        out.enterln("tops: ");
        return true;
    }

    @Override
    public boolean visitTop(key_t key, val_t val) {
        out.print(key);
        out.enterln(": ");
        out.print(val);
        out.leave();
        return true;
    }

    @Override
    public void endTops() {
//        out.leave();
    }

    @Override
    public boolean beginRanges() {
//        out.enterln("ranges: ");
        return true;
    }

    @Override
    public boolean visitRange(IRange<? extends key_t> rangeKey, val_t val) {
        out.print(rangeKey);
        out.enterln(": ");
        out.print(val);
        out.leave();
        return true;
    }

    @Override
    public void endRanges() {
//        out.leave();
    }

    @Override
    public void visitDefault(val_t value) {
        out.print("default: ");
        out.println(value);
    }

}
