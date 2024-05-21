package net.bodz.swt.ui.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

public class ButtonEntryBuilder
        extends AbstractFieldEntryBuilder<Boolean, Button, ButtonEntry, ButtonEntryBuilder> {

    public ButtonEntryBuilder(FieldEntryForm context) {
        super(context);
    }

    public ButtonEntryBuilder radio() {
        style |= SWT.RADIO;
        return this;
    }

    public ButtonEntryBuilder checkbox() {
        style |= SWT.CHECK;
        return this;
    }

    public ButtonEntryBuilder check() {
        initialValue = true;
        return this;
    }

    @Override
    protected ButtonEntry build2(Control labelControl) {
        Button button = new Button(context.parent, style);
        if (setter != null)
            button.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    setter.accept(button.getSelection());
                }
            });

        FormData formData = new FormData();
        formData.top = new FormAttachment(labelControl, 0, SWT.CENTER);
        formData.left = new FormAttachment(0, context.hSkip);
        button.setLayoutData(formData);
        return new ButtonEntry(labelControl, button);
    }

}
