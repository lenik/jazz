package net.bodz.bas.t.specmap;

public interface ISpecMapVisitor<key_t, val_t> {

    default boolean beginTops() {
        return true;
    }

    default boolean visitTop(key_t key, val_t val) {
        return true;
    }

    default void endTops() {
    }

    default boolean beginRanges() {
        return true;
    }

    default boolean visitRange(IRange<? extends key_t> rangeKey, val_t val) {
        return true;
    }

    default void endRanges() {
    }

    default void visitDefault(val_t value) {
    }

    default void visitValue(val_t value) {
    }

}
