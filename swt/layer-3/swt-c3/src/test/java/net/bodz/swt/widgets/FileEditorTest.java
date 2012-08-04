package net.bodz.swt.widgets;

import net.bodz.swt.c3.file.FileEditor;
import net.bodz.swt.c3.file.FileSelector;
import net.bodz.swt.c3.test.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.junit.Test;

public class FileEditorTest {

    @Test
    public void test()
            throws Exception {
        ControlTestApp app = new ControlTestApp();
        app.holder.setLayout(new GridLayout(1, false));

        final Button dirMode = new Button(app.holder, SWT.CHECK);
        dirMode.setText("&Directory Mode");

        final FileEditor editor = new FileEditor(app.holder, SWT.NONE);
        editor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final FileSelector fs = new FileSelector();
        fs.setDialogTitle("Dialog Title!!");
        fs.setDialogMessage("Dialog Message!!\nDialog Message more text..!!");
        editor.setFileSelector(fs);

        dirMode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean d = dirMode.getSelection();
                if (d)
                    fs.setDirectoryMode(0);
                else
                    fs.setFileMode(SWT.OPEN);
            }
        });

        app.run();
    }

}
