package net.bodz.bas.trait.spi;

import static net.bodz.bas.trait.spi.ITraitsProvider.*;
import net.bodz.bas.lang.mi.IQueryable;

enum BuiltinProviderOrder {

    /**
     * {@link IQueryable} is always queryed first.
     */
    immediateQuery(PRIORITY_HIGH), //

    /**
     * If the getTraits() method is not added for traits purpose, then annotation should be used.
     */
    annotation(PRIORITY_NORMAL), //

    /**
     * If the friend-named classes are not for traits purpose, then getTraits() should be used.
     */
    queryMethod(PRIORITY_LOW), //

    /**
     * Search in the same package namespace first, because this is in most cases, and a little speed
     * gain.
     */
    friend(PRIORITY_LOW), //
    basFriend(PRIORITY_LOW), //

    ;

    private int priorityBase;

    private BuiltinProviderOrder(int priorityBase) {
        this.priorityBase = priorityBase;
    }

    public int getPriority() {
        return priorityBase + this.ordinal();
    }

}
