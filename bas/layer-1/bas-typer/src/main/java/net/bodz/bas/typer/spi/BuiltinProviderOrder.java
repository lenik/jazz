package net.bodz.bas.typer.spi;

import static net.bodz.bas.typer.spi.ITyperProvider.*;

import net.bodz.bas.rtx.IQueryable;

enum BuiltinProviderOrder {

    /**
     * {@link IQueryable} is always queryed first.
     */
    immediateQuery(PRIORITY_HIGH), //

    /**
     * If the getTypers() method is not added for typers purpose, then annotation should be used.
     */
    annotation(PRIORITY_NORMAL), //

    /**
     * If the friend-named classes are not for typers purpose, then getTypers() should be used.
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
