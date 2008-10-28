package net.bodz.swt.gui;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.gui.RenderException;

import org.eclipse.swt.widgets.Control;

public interface RenderContext {

    Interaction interact(Control control);

    void addEffects(Control control, GUIVar<?> var) throws RenderException;

}
