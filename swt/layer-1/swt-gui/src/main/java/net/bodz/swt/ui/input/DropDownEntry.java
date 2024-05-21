package net.bodz.swt.ui.input;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

public class DropDownEntry
        extends AbstractFieldEntry<String, Combo, DropDownEntry> {

    Combo list;

    public DropDownEntry(Control labelControl, Combo input) {
        super(labelControl, input);
        this.list = input;
    }

    @Override
    public String getValue() {
        return list.getText();
    }

    @Override
    public void setValue(String value) {
        list.setText(value);
    }

    public int getIndex() {
        return list.getSelectionIndex();
    }

    public void setIndex(int index) {
        list.select(index);
    }

}
