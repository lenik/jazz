package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;
import net.bodz.swt.gui.news.GUIVar;

/**
 * @see GUIVar
 */
@Deprecated
public interface GUIItem extends Comparable<GUIItem> {

    GUIHint getHint();

    Object get(Object obj) throws GUIAccessException;

    void set(Object obj, Object value) throws GUIAccessException;

    void check(Object obj, Object value) throws CheckException,
            GUIAccessException;

    void execute(Object obj, Object... args) throws GUIAccessException;

}
