package net.bodz.swt.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public interface WidgetCreator {

    Widget create(Composite parent, int style);

}
