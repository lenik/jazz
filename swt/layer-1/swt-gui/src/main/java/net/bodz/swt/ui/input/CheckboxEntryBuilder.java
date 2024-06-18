package net.bodz.swt.ui.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

public class CheckboxEntryBuilder
        extends AbstractFieldEntryBuilder<Boolean, Button, CheckboxEntry, CheckboxEntryBuilder> {

    public CheckboxEntryBuilder(FieldEntryForm context) {
        super(context);
        style |= SWT.CHECK;
    }

    @Override
    protected CheckboxEntry build2(Control labelControl) {
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
        return new CheckboxEntry(labelControl, button);
    }

}
