package net.bodz.swt.gui;

import org.eclipse.swt.widgets.Composite;

public interface GUIBuilder {

    Composite build(GUIClass guiclass, Composite parent, int style)
            throws BuildException;

}
