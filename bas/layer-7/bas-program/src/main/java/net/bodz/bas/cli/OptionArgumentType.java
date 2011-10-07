package net.bodz.bas.cli;

import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.IValidator;

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

    /**
     * @param optionName
     *            The prefix dash (single/double) are removed.
     * @param optionParam
     *            The parameter, <code>null</code> if not specified.
     * @param holder
     *            The scalar object, or the holder array/collection/map.
     * @param parser
     *            Value parser.
     * @param validator
     *            Value validator.
     */
    public Object parse(String optionName, String optionParam, Object holder, IParser<?> parser, IValidator<?> validator) {
        switch (this) {
        case SWITCH:
            assert optionParam == null;

        case SCALAR:
            
        case ARRAY:
        case COLLECTION:
        case MAP:
        }
    }

}
