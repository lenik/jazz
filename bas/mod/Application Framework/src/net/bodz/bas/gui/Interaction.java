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

    String prompt(String title);

    String prompt(String title, Object detail);

    String prompt(String title, String initial);

    String prompt(String title, Object detail, String initial);

    <T> T prompt(String title, Class<T> type, T initial);

    <T> T prompt(String title, Object detail, Class<T> type, T initial);

    <K> K choice(String title, Map<K, ?> candidates);

    <K> K choice(String title, Object detail, Map<K, ?> candidates);

    int choice(String title, List<?> candidates);

    int choice(String title, Object detail, List<?> candidates);

}
