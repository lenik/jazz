package net.bodz.bas.t.specmap;

import net.bodz.bas.io.ITreeOut;

public class SpecNodeDumper<node_t extends ISpecNode<node_t, key_t, val_t>, key_t, val_t>
        implements
            ISpecNodeVisitor<node_t, key_t, val_t> {

    ITreeOut out;

    public SpecNodeDumper(ITreeOut out) {
        this.out = out;
    }

    @Override
    public boolean beginTops() {
//        out.enterln("tops: ");
        return true;
    }

    @Override
    public boolean visitTop(key_t key, node_t val) {
        out.print(key);
        out.println(": ");
        out.enter();
        val.accept(this);
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
    public boolean visitRange(IRange<? extends key_t> rangeKey, node_t val) {
        out.print(rangeKey);
        out.println(": ");
        out.enter();
        val.accept(this);
        out.leave();
        return true;
    }

    @Override
    public void endRanges() {
//        out.leave();
    }

    @Override
    public void visitDefault(node_t value) {
        out.enterln("default: ");
        value.accept(this);
        out.leave();
    }

    @Override
    public void visitNodeValue(val_t value) {
        out.print("value: ");
        out.print(value);
        out.println();
    }

}
