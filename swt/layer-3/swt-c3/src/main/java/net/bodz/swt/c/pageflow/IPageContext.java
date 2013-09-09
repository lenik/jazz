package net.bodz.swt.c.pageflow;

import org.eclipse.swt.widgets.Shell;

public interface IPageContext {

    Shell getShell();

    IBook getBook();

    History getHistory();

    AbstractPageFlow getPageFlow();

}
