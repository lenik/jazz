package net.bodz.bas.meta.source;

public enum ChainUsage {

    /** didn't intend to be chained */
    FORBIDDEN,

    /** May or may not be called */
    OPTIONAL,

    /** Preferred to be chained, warnings if not be chained */
    PREFERRED,

    /** Must be chained */
    MUST,

}
