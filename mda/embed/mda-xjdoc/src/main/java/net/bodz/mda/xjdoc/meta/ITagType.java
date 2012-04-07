package net.bodz.mda.xjdoc.meta;

import java.util.Map.Entry;

public interface ITagType {

    /**
     * Parse the doc text.
     * 
     * @return Instance of {@link Entry} will result in parameterized attributes.
     */
    Object parse(String text);

    /**
     * Format the value object.
     * 
     * @param value
     *            Maybe instance of {@link Entry} to include the attribute sub-keys.
     */
    String format(Object value);

    String format(Object key, Object value);

}
