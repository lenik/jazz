package net.bodz.swt.ui.input;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

public class CheckboxEntry
        extends AbstractFieldEntry<Boolean, Button, CheckboxEntry> {

    private Button checkbox;
    boolean isNull;

    public CheckboxEntry(Control labelControl, Button input) {
        super(labelControl, input);
        this.checkbox = input;
    }

    @Override
    public Boolean getValue() {
        if (isNull)
            return null;
        else
            return checkbox.getSelection();
    }

    @Override
    public synchronized void setValue(Boolean value) {
        isNull = value == null;
        if (isNull)
            value = false;
        boolean _val = value.booleanValue();
        boolean prev = checkbox.getSelection();
        if (prev != _val) {
            checkbox.setSelection(_val);
        }
    }

}
