package net.bodz.swt.auto;

import java.lang.reflect.Field;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface VisualType {

    Class<?>[] supportTypes();

    Control createControl(Composite parent);

    void removeControl(Control control);

    void render(Object data, Control target);

    void render(Object data, Field field, Control target);

    void render(Object data, String field, Control target);

    Object capture(Control source, Object dataInit);

    void capture(Control source, Object data, Field field);

    void capture(Control source, Object data, String field);

}
