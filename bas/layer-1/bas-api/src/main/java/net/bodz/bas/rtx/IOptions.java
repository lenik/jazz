package net.bodz.bas.rtx;

public interface IOptions
        extends Iterable<IOption> {

    int size();

    /**
     * @return <code>null</code> if the parameter is undefined.
     */
    IOption getOption(String id);

    /**
     * @return <code>null</code> if the parameter is undefined.
     */
    IOption getOption(Class<?> clazz);

    /**
     * Get optional parameter.
     * 
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(String id);

    /**
     * Get optional parameter.
     * 
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    <T> T get(String id, T defaultValue);

    /**
     * Get optional parameter.
     * 
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(Class<T> clazz);

    /**
     * Get optional parameter.
     * 
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    <T> T get(Class<T> clazz, T defaultValue);

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    int getInt(String id);

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    int getInt(String id, int defaultValue);

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    long getLong(String id);

    /**
     * @return Integer value. If the value is string, it will be parsed. Returns <code>0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    long getLong(String id, long defaultValue);

    /**
     * @return Float value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    float getFloat(String id);

    /**
     * @return Float value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    float getFloat(String id, float defaultValue);

    /**
     * @return Double value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    double getDouble(String id);

    /**
     * @return Double value. If the value is string, it will be parsed. Returns <code>0.0</code> if
     *         the option isn't defined.
     * @throws NumberFormatException
     *             If the value is non-numeric string.
     */
    double getDouble(String id, double defaultValue);

    boolean getBoolean(String id);

    boolean getBoolean(String id, boolean defaultValue);

}
