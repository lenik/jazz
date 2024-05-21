package net.bodz.swt.ui.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class TextEntryBuilder
        extends AbstractFieldEntryBuilder<String, Text, TextEntry, TextEntryBuilder> {

    public TextEntryBuilder(FieldEntryForm context) {
        super(context);
        style |= SWT.BORDER;
    }

    @Override
    protected TextEntry build2(Control labelControl) {
        Text textControl = new Text(context.parent, style);
        if (setter != null)
            textControl.addModifyListener(e -> setter.accept(textControl.getText()));

        FormData formData = new FormData();
        formData.top = new FormAttachment(labelControl, 0, SWT.CENTER);
        formData.left = new FormAttachment(0, context.hSkip);
        formData.right = new FormAttachment(100);
        textControl.setLayoutData(formData);

        return new TextEntry(labelControl, textControl);
    }

}
