package net.bodz.swt.gui.pfl;

import java.util.Collection;

import net.bodz.bas.text.locale.NLSDict;
import net.bodz.bas.types.TreePath;

public interface Book {

    String getTitle();

    TreePath getFirst();

    boolean contains(TreePath path);

    /**
     * @return <code>null</code> if specified page isn't existed.
     */
    Page getPage(TreePath path);

    Collection<PageMethod> getMethods();

    NLSDict getDict();

}
