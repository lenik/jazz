package net.bodz.swt.gui.pfl;

import org.eclipse.swt.widgets.Shell;

public interface IPageContext {

    Shell getShell();

    IBook getBook();

    History getHistory();

    AbstractPageFlow getPageFlow();

}
