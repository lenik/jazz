package net.bodz.bas.fmt.json;

public enum JsonOutState {

    /**
     * expect a value
     */
    INIT,

    /**
     * expect a key
     */
    OBJECT,

    OBJECT_AUTOFIX,

    /**
     * expect a value
     */
    KEY,

    /**
     * expect a value
     */
    ARRAY,

}
