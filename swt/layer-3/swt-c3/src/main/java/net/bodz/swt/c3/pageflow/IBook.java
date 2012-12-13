package net.bodz.swt.c3.pageflow;

import java.util.Collection;

import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.t.pojo.PathEntries;

public interface IBook {

    String getTitle();

    PathEntries getFirst();

    boolean contains(PathEntries path);

    /**
     * @return <code>null</code> if specified page isn't existed.
     */
    IPage getPage(PathEntries path);

    Collection<PageMethod> getMethods();

    NLS getDict();

}
