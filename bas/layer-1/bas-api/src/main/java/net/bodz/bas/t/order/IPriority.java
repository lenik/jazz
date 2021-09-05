package net.bodz.bas.t.order;

public interface IPriority {

    default int getPriority() {
        return 0;
    }

}
