package net.bodz.bas.t.specmap;

public interface ISpecMapVisitor<key_t, val_t> {

    default boolean beginTops() {
        return false;
    }

    default boolean visitTop(key_t key, val_t val) {
        return true;
    }

    default void endTops() {
    }

    default boolean beginRanges() {
        return false;
    }

    default boolean visitRange(IRange<? extends key_t> rangeKey, val_t val) {
        return true;
    }

    default void endRanges() {
    }

    default void visitDefault(val_t value) {
    }

    default boolean beginValue(SpecLayer layer, Object layerKey) {
        return true;
    }

    default void visitValue(val_t value) {
    }

    default void endValue() {
    }

}
