package net.bodz.swt.c.pageflow;

import java.util.Collection;

import net.bodz.bas.i18n.nls.INlsTranslator;
import net.bodz.bas.t.pojo.PathEntries;

public interface IBook {

    String getTitle();

    PathEntries getFirst();

    boolean contains(PathEntries path);

    /**
     * @return <code>null</code> if specified page isn't existed.
     */
    IPage getPage(PathEntries path);

    Collection<IPage> getAllPages();

    Collection<PageMethod> getMethods();

    INlsTranslator getDict();

}
