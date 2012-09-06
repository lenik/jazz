package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.Set;

import net.bodz.bas.util.variant.ILookupMap;
import net.bodz.bas.util.variant.IVariantLookupMap;

public interface NLS
        extends IVariantLookupMap<String> {

    NLS getParent();

    /**
     * The descriptive name.
     * 
     * @return A non-<code>null</code> name string.
     */
    String getName();

    String getPath();

    /**
     * Get the preferred locale.
     * <p>
     * Only the entries with the same locale or compatible locale will be returned in next calls.
     */
    Locale getPreferredLocale();

    /**
     * Set the preferred locale.
     * <p>
     * Change the preferred locale will also change the return values returned by
     * {@link #contains(String)}, {@link #get(String)}, etc.
     */
    void setPreferredLocale(Locale preferredLocale);

    /**
     * The {@link ILookupMap#keySet()} is not used.
     */
    @Deprecated
    @Override
    Set<String> keySet();

    /**
     * Returns whether a specified dict key is defined in the dict.
     * 
     * @param key
     *            A non-<code>null</code> dict key to be queried.
     * @return <code>true</code> If the dict key is defined.
     */
    @Override
    boolean containsKey(String key);

    /**
     * Get the value of the dict entry with the specified <code>key</code>.
     * 
     * @param key
     *            The non-<code>null</code> dict key to be queried.
     * @return The value of the dict entry. If the entry isn't existed, <code>null</code> is
     *         returned, too.
     */
    @Override
    Object get(String key);

    /**
     * Get the value of the dict entry in string format with the specified <code>key</code>
     * specified.
     * 
     * @param key
     *            A non-<code>null</code> dict key to be queried.
     * @return The value of the dict entry. If the entry isn't existed, <code>defaultValue</code> is
     *         returned, too.
     */
    @Override
    Object get(String key, Object defaultValue);

    /**
     * Get the value of the dict entry with the specified <code>key</code>, and format it to string.
     * 
     * @param key
     *            A non-<code>null</code> dict key to be queried.
     * @return A converted string of the value of the dict entry. If the entry isn't existed,
     *         <code>null</code> is returned, too.
     */
    @Override
    String getString(String key);

    /**
     * Get the value of the dict entry in string format, with the specified <code>key</code>, with a
     * default value specified.
     * 
     * @param key
     *            A non-<code>null</code> dict key to be queried.
     * @return A converted string of the value of the dict entry. If the entry isn't existed,
     *         <code>defaultValue</code> is returned, too.
     */
    @Override
    String getString(String key, String defaultValue);

    /**
     * Format a string using the format get from the dict entry with the specified
     * <code>formatKey</code>.
     * <p>
     * This is just the same as
     * 
     * <pre>
     * String.format(getString(formatKey), args)
     * </pre>
     * 
     * The <code>formatKey</code> must exist, otherwise {@link NullPointerException} will be thrown.
     * 
     * @param formatKey
     *            A non-<code>null</code> dict key to be queried.
     * @param args
     *            Optional arguments to the format string.
     * @return A non-<code>null</code>formated string, using the format from the value of the dict
     *         entry.
     * @throws NullPointerException
     *             If <code>formatKey</code> is <code>null</code>.
     */
    @Override
    String format(String formatKey, Object... args);

    /**
     * Translate a name.
     * <p>
     * This is just a shortcut for: <code>getString(name, name)</code>, however returns
     * <code>null</code> for <code>null</code> name.
     * 
     * @param name
     *            The name to be translated.
     * @return The translated name, or the original <code>name</code> if no translation is
     *         available. Return <code>null</code> if the <code>name</code> is <code>null</code>.
     */
    String tr(String name);

}
