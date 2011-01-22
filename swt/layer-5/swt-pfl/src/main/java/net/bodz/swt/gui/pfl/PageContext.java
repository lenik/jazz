package net.bodz.swt.gui.pfl;

import org.eclipse.swt.widgets.Shell;

public interface PageContext {

    Shell getShell();

    Book getBook();

    History getHistory();

    PageFlow getPageFlow();

}
