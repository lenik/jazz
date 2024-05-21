package net.bodz.swt.ui.input;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

public class ButtonEntry
        extends AbstractFieldEntry<Boolean, Button, ButtonEntry> {

    public Button button;

    public ButtonEntry(Control labelControl, Button input) {
        super(labelControl, input);
        this.button = input;
    }

    @Override
    public Boolean getValue() {
        return input.getSelection();
    }

    @Override
    public void setValue(Boolean value) {
        button.setSelection(value);
    }

}
