package net.bodz.bas.t.specmap;

public class StringKey2IntVisitor<val_t>
        implements
            ISpecMapVisitor<Integer, val_t> {

    ISpecMapVisitor<? super String, ? super val_t> stringKeyVisitor;

    public StringKey2IntVisitor(ISpecMapVisitor<? super String, ? super val_t> stringKeyVisitor) {
        this.stringKeyVisitor = stringKeyVisitor;
    }

    @Override
    public boolean beginTops() {
        return stringKeyVisitor.beginTops();
    }

    @Override
    public boolean visitTop(Integer key, val_t val) {
        String strkey = String.valueOf(key);
        return stringKeyVisitor.visitTop(strkey, val);
    }

    @Override
    public void endTops() {
        stringKeyVisitor.endTops();
    }

    @Override
    public boolean beginRanges() {
        return stringKeyVisitor.beginRanges();
    }

    @Override
    public boolean visitRange(IRange<? extends Integer> rangeKey, val_t val) {
        IRange<String> strRange;
        if (rangeKey.isStartEnd()) {
            strRange = new StartEndRange<String>( //
                    String.valueOf(rangeKey.getStart()), //
                    String.valueOf(rangeKey.getEnd()));
        } else {
            strRange = new StartStopRange<String>( //
                    String.valueOf(rangeKey.getStart()), //
                    String.valueOf(rangeKey.getStop()));
        }
        return stringKeyVisitor.visitRange(strRange, val);
    }

    @Override
    public void endRanges() {
        stringKeyVisitor.endRanges();
    }

    @Override
    public void visitDefault(val_t value) {
        stringKeyVisitor.visitDefault(value);
    }

    @Override
    public boolean beginValue(SpecLayer layer, Object layerKey) {
        return stringKeyVisitor.beginValue(layer, layerKey);
    }

    @Override
    public void visitValue(val_t value) {
        stringKeyVisitor.visitValue(value);
    }

    @Override
    public void endValue() {
        stringKeyVisitor.endValue();
    }

}
