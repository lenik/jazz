package net.bodz.swt.c3.pageflow;

import java.util.Collection;

import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.t.tree.TreePath;

public interface IBook {

    String getTitle();

    TreePath getFirst();

    boolean contains(TreePath path);

    /**
     * @return <code>null</code> if specified page isn't existed.
     */
    IPage getPage(TreePath path);

    Collection<PageMethod> getMethods();

    NLS getDict();

}
