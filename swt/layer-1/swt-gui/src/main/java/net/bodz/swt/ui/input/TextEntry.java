package net.bodz.swt.ui.input;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.c.object.Nullables;

public class TextEntry
        extends AbstractFieldEntry<String, Text, TextEntry> {

    private Text textControl;
    boolean isNull;

    public TextEntry(Control labelControl, Text input) {
        super(labelControl, input);
        this.textControl = input;
    }

    @Override
    public String getValue() {
        if (isNull)
            return null;
        else
            return textControl.getText();
    }

    @Override
    public synchronized void setValue(String value) {
        isNull = value == null;
        if (isNull)
            value = "";
        String prev = textControl.getText();
        if (! Nullables.equals(prev, value)) {
            textControl.setText(value);
        }
    }

}
