package net.bodz.bas.rtx;

import java.util.NoSuchElementException;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.decl.NotNull;

public interface IOptions
        extends Iterable<IOption> {

    int size();

    IOption getOption(@NotNull String id);

    default IOption getOption(@NotNull Class<?> clazz) {
        String id = clazz.getCanonicalName();
        return getOption(id);
    }

    /**
     * Get optional parameter.
     *
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    default <T> T get(@NotNull String id) {
        return get(id, null);
    }

    /**
     * Get optional parameter.
     *
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    default <T> T get(@NotNull String id, T defaultValue) {
        IOption parameter = getOption(id);
        if (parameter == null)
            return defaultValue;
        else
            return parameter.getValue();
    }

    /**
     * Get optional parameter.
     *
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    default <T> T get(@NotNull Class<T> clazz) {
        String id = clazz.getCanonicalName();
        return get(id, null);
    }

    /**
     * Get optional parameter.
     *
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    default <T> T get(@NotNull Class<T> clazz, T defaultValue) {
        String id = clazz.getCanonicalName();
        return get(id, defaultValue);
    }

    default <T> T require(String id) {
        T o = get(id);
        if (o == null)
            throw new NoSuchElementException(id);
        return o;
    }

    default <T> T require(@NotNull Class<T> clazz) {
        T o = get(clazz);
        if (o == null)
            throw new NoSuchElementException(clazz.toString());
        return o;
    }

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default int getInt(String id) {
        return getInt(id, 0);
    }

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default int getInt(String id, int defaultValue) {
        Object value = get(id);

        if (value == null)
            return defaultValue;

        if (value instanceof Number)
            return ((Number) value).intValue();

        if (value instanceof String)
            return Integer.parseInt((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default long getLong(String id) {
        return getLong(id, 0L);
    }

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default long getLong(String id, long defaultValue) {
        Object value = get(id);

        if (value == null)
            return defaultValue;

        if (value instanceof Number)
            return ((Number) value).longValue();

        if (value instanceof String)
            return Long.parseLong((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    /**
     * @return Float value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default float getFloat(String id) {
        return getFloat(id, 0.0f);
    }

    /**
     * @return Float value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default float getFloat(String id, float defaultValue) {
        Object value = get(id);

        if (value == null)
            return defaultValue;

        if (value instanceof Number)
            return ((Number) value).floatValue();

        if (value instanceof String)
            return Float.parseFloat((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    /**
     * @return Double value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default double getDouble(String id) {
        return getDouble(id, 0.0f);
    }

    /**
     * @return Double value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     * the option isn't defined.
     * @throws NumberFormatException If the value is non-numeric string.
     */
    default double getDouble(String id, double defaultValue) {
        Object value = get(id);

        if (value == null)
            return defaultValue;

        if (value instanceof Number)
            return ((Number) value).doubleValue();

        if (value instanceof String)
            return Double.parseDouble((String) value);

        throw new IllegalUsageException("Not a numeric value: " + value);
    }

    default boolean getBoolean(String id) {
        return getBoolean(id, false);
    }

    default boolean getBoolean(String id, boolean defaultValue) {
        Object value = get(id);

        if (value == null)
            return defaultValue;

        if (value instanceof Boolean)
            return ((Boolean) value);

        if (value instanceof String)
            return Boolean.parseBoolean((String) value);

        throw new IllegalUsageException("Not a boolean value: " + value);
    }

    IOptions NULL = new NullOptions();

}
