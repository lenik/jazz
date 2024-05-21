package net.bodz.swt.ui.input;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

public class DropDownEntryBuilder
        extends AbstractFieldEntryBuilder<String, Combo, DropDownEntry, DropDownEntryBuilder> {

    ArrayList<String> options = new ArrayList<String>();

    public DropDownEntryBuilder(FieldEntryForm context) {
        super(context);
        style = SWT.DROP_DOWN;
        style |= SWT.READ_ONLY;
    }

    public DropDownEntryBuilder option(String option) {
        return option(option, false);
    }

    public DropDownEntryBuilder option(String option, boolean selected) {
        if (option == null)
            throw new NullPointerException("option");
        if (selected)
            initialValue = option;
        options.add(option);
        return this;
    }

    public DropDownEntryBuilder options(String... options) {
        for (String opt : options)
            this.options.add(opt);
        return this;
    }

    @Override
    protected DropDownEntry build2(Control labelControl) {
        Combo listControl = new Combo(context.parent, style);
        for (String option : options)
            listControl.add(option);

        FormData formData = new FormData();
        formData.top = new FormAttachment(labelControl, 0, SWT.CENTER);
        formData.left = new FormAttachment(0, context.hSkip);
//        formData.right = new FormAttachment(100, SWT.RIGHT);
        listControl.setLayoutData(formData);

        DropDownEntry entry = new DropDownEntry(labelControl, listControl);
        if (setter != null)
            listControl.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    String value = entry.getValue();
                    setter.accept(value);
                }
            });
        return entry;
    }

}
