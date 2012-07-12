package net.bodz.bas.cli.model;


public enum OptionArgumentType {

    /**
     * Specify <code>--option</code> or <code>--no-option</code> to turn on/off the boolean option.
     */
    SWITCH,

    /**
     * Specify <code>--option param</code>, where <code>param</code> is fully parsed.
     */
    SCALAR,

    /**
     * Specify <code>--option param</code>, the array is realloc/expanded with the parameter
     * appeneded.
     */
    ARRAY,

    /**
     * Specify <code>--option param</code>, each parameter are then added to the collection.
     */
    COLLECTION,

    /**
     * Specify <code>--option name=value</code> where <code>name</code> and <code>value</code> are
     * parsed as key/value type of the corresponding map.
     * 
     * Each entry is then added to the map.
     */
    MAP,

    ;

}
