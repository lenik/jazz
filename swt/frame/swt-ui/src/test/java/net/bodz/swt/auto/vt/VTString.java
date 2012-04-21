package net.bodz.swt.auto.vt;

import net.bodz.swt.auto.AbstractVisualType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class VTString
        extends AbstractVisualType {

    @Override
    public Control createControl(Composite parent) {
        Text text = new Text(parent, SWT.NONE);
        return text;
    }

    @Override
    public Object capture(Control source, Object data) {
        assert data != null;
        assert source instanceof Text;
        Text text = (Text) source;
        return text.getText();
    }

    @Override
    public void render(Object data, Control target) {
        assert data != null;
        assert target instanceof Text;
        Text text = (Text) target;
        text.setText(data.toString());
    }

    @Override
    public Class<?>[] supportTypes() {
        return SUPPORT_TYPES;
    }

    static Class<?>[] SUPPORT_TYPES = { String.class, };

}
