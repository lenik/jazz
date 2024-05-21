package net.bodz.swt.ui.input;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

public class FieldEntryForm {

    Composite parent;
    List<IFieldEntry<?>> entries = new ArrayList<>();

    int hSkip = 16 * 12;
    int vSkip = 20;

    public FieldEntryForm(Composite parent) {
        this.parent = parent;
    }

    IFieldEntry<?> getLast() {
        if (entries.isEmpty())
            return null;
        else
            return entries.get(entries.size() - 1);
    }

    public TextEntryBuilder text() {
        return new TextEntryBuilder(this);
    }

    public ButtonEntryBuilder button() {
        return new ButtonEntryBuilder(this);
    }

    public ButtonEntryBuilder checkbox() {
        return new ButtonEntryBuilder(this).checkbox();
    }

    public ButtonEntryBuilder radio() {
        return new ButtonEntryBuilder(this).radio();
    }

    public DropDownEntryBuilder dropDown() {
        return new DropDownEntryBuilder(this);
    }

    public DropDownEntryBuilder dropDown(String... options) {
        return new DropDownEntryBuilder(this).options(options);
    }

}
