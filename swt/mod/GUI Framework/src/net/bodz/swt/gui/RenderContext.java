package net.bodz.swt.gui;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.rt.Interaction;

import org.eclipse.swt.widgets.Control;

public interface RenderContext {

    Interaction interact(Control control);

    void addEffects(Control control, GUIVar<?> var) throws RenderException;

}
