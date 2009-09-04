package net.bodz.swt.gui.pfl;

import java.util.Collection;

import net.bodz.bas.text.locale.NLSDict;
import net.bodz.bas.types.TreePath;

public interface Book {

    TreePath getFirst();

    boolean contains(TreePath path);

    Page getPage(TreePath path);

    Collection<PageMethod> getMethods();

    NLSDict getDict();

}
