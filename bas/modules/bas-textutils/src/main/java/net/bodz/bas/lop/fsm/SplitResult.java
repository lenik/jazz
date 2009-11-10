package net.bodz.bas.lop.fsm;

public class SplitResult {

    private final CharSet[] _this;
    private final CharSet[] that;
    private final CharSet[] common;

    public SplitResult(Object _this, Object that, Object common) {
        this._this = toArray(_this);
        this.that = toArray(that);
        this.common = toArray(common);
    }

    static final CharSet[] toArray(Object o) {
        if (o == null)
            return new CharSet[0];
        if (o instanceof CharSet)
            return new CharSet[] { (CharSet) o };
        return ((CharSet[]) o);
    }

    public CharSet[] getThis() {
        return _this;
    }

    public CharSet[] getThat() {
        return that;
    }

    public CharSet[] getCommon() {
        return common;
    }

}
