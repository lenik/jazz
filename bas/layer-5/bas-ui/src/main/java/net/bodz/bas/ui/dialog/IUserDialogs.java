package net.bodz.bas.ui.dialog;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.fn.IExecutableX;

/**
 * may throw exceptions in future:
 * <ul>
 * <li>TimeoutException
 * <li>UserCanceledException
 * </ul>
 */
public interface IUserDialogs {

    void alert(String title);

    void alert(String title, Object detail);

    boolean confirm(String title);

    boolean confirm(String title, Object detail);

    /** @return index of selected proposal, or -1 if canceled */
    int ask(String title, IDirectiveCommand... proposals);

    /** @return index of selected proposal, or -1 if canceled */
    int ask(String title, Object detail, IDirectiveCommand... proposals);

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
    <K> K choice(String title, Map<K, ?> candidates);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K choice(String title, Map<K, ?> candidates, K initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K choice(String title, Object detail, Map<K, ?> candidates);

    /**
     * @return <code>null</code> if canceled
     */
    <K> K choice(String title, Object detail, Map<K, ?> candidates, K initial);

    /**
     * @return -1 if canceled
     */
    int choice(String title, List<?> candidates);

    /**
     * @return -1 if canceled
     */
    int choice(String title, List<?> candidates, int initial);

    /**
     * @return -1 if canceled
     */
    int choice(String title, Object detail, List<?> candidates);

    /**
     * @return -1 if canceled
     */
    int choice(String title, Object detail, List<?> candidates, int initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> Set<K> choices(String title, Map<K, ?> candidates, @SuppressWarnings("unchecked") K... initial);

    /**
     * @return <code>null</code> if canceled
     */
    <K> Set<K> choices(String title, Object detail, Map<K, ?> candidates, @SuppressWarnings("unchecked") K... initial);

    /**
     * @return <code>null</code> if canceled
     */
    int[] choices(String title, List<?> candidates, int... initial);

    /**
     * @return <code>null</code> if canceled
     */
    int[] choices(String title, Object detail, List<?> candidates, int... initial);

    <X extends Exception> int tryBlock(IExecutableX<X> runnable)
            throws X;

    /**
     * @param maxRetry
     *            retry any times if {@link TryBlock#INFINITE}.
     * @return
     * @see TryBlock#DONE
     * @see TryBlock#MAXRETRIED
     * @see TryBlock#IGNORED
     * @see TryBlock#CANCELED
     */
    <X extends Exception> int tryBlock(IExecutableX<X> runnable, int maxRetry)
            throws X;

    // void showProgress(Runnable runnable);

    void showBusy(Runnable runnable);

}
