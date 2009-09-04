package net.bodz.swt.gui.pfl;

import net.bodz.bas.types.SimpleRequest;
import net.bodz.bas.types.TreePath;

public interface ServiceContext {

    PageContext getPageContext();

    SimpleRequest getRequest();

    TreePath getReferrerPath();

}
