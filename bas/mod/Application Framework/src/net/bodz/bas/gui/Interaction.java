package net.bodz.bas.gui;

import java.util.List;
import java.util.Map;

/**
 * may throw exceptions in future:
 * <ul>
 * <li>TimeoutException
 * <li>UserCanceledException
 * </ul>
 */
public interface Interaction {

    void alert(String title);

    void alert(String title, Object detail);

    boolean confirm(String title);

    boolean confirm(String title, Object detail);

    /**
     * @return <code>null</code> if canceled
     */
    String prompt(String title);

    /**
     * @return <code>null</code> if canceled
     */
    String prompt(String title, Object detail);

    /**
     * @return <code>null</code> if canceled
     */
    String prompt(String title, String initial);

    /**
     * @return <code>null</code> if canceled
     */
    String prompt(String title, Object detail, String initial);

    /**
     * @return <code>null</code> if canceled
     */
    <T> T prompt(String title, Class<T> type, T initial);

    /**
     * @return <code>null</code> if canceled
     */
    <T> T prompt(String title, Object detail, Class<T> type, T initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K choice(String title, Map<K, ?> candidates, K initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K choice(String title, Object detail, Map<K, ?> candidates, K initial);

    /**
     * @return -1 if canceled
     */
    int choice(String title, List<?> candidates, int initial);

    /**
     * @return -1 if canceled
     */
    int choice(String title, Object detail, List<?> candidates, int initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K[] choices(String title, Map<K, ?> candidates, K... initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K[] choices(String title, Object detail, Map<K, ?> candidates,
            K... initial);

    /**
     * @return <code>null</code> if canceled
     */
    int[] choices(String title, List<?> candidates, int... initial);

    /**
     * @return <code>null</code> if canceled
     */
    int[] choices(String title, Object detail, List<?> candidates,
            int... initial);

}
