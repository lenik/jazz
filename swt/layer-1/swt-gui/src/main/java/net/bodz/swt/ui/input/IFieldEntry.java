package net.bodz.swt.ui.input;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Control;

public interface IFieldEntry<T> {

    Control getLabelControl();

    Control getInputControl();

    T getValue();

    /**
     * @throws IllegalArgumentException
     * @throws SWTException
     */
    void setValue(T value);

}
