package net.bodz.swt.widgets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.CorruptedStateError;
import net.bodz.swt.widgets.test.ControlTestApp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.junit.Test;

public class _ListEditorTest {

    @Test
    public void test()
            throws Exception {
        List<File> files = new ArrayList<File>();
        String s = "entry";
        for (int i = 0; i < 20; i++) {
            files.add(new File(s));
            s += " *";
        }

        ControlTestApp app = new ControlTestApp();

        app.holder.setLayout(new GridLayout(1, true));
        final Button allowArrangeCheck = new Button(app.holder, SWT.CHECK);
        allowArrangeCheck.setText("&Allow arrange");

        final _ListEditor<File> filesEditor = new _ListEditor<File>(app.holder, SWT.NONE) {
            @Override
            protected File createObject() {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                String path = dialog.open();
                if (path == null)
                    return null;
                return new File(path);
            }
        };
        filesEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        filesEditor.setText("&Files: ");

        allowArrangeCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean allow = allowArrangeCheck.getSelection();
                filesEditor.setAllowArrange(allow);
            }
        });

        filesEditor.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkSelection("select");
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                checkSelection("dselect");
            }

            void checkSelection(String prefix) {
                File file = filesEditor.getSelection();
                System.out.println(prefix + ": " + file);
                // check if listbox/list<> is bad linked
                String disp = filesEditor.getSelectionText();
                if (!disp.equals(String.valueOf(file)))
                    throw new CorruptedStateError("file=" + file + ", disp=" + disp);
            }
        });
        filesEditor.setList(files);

        filesEditor.setForeground(app.display.getSystemColor(SWT.COLOR_WHITE));
        filesEditor.setBackground(app.display.getSystemColor(SWT.COLOR_BLUE));

        FontData fontData = filesEditor.getFont().getFontData()[0];
        fontData.setHeight(15);
        Font font = new Font(app.display, fontData);
        filesEditor.setFont(font);

        app.run();
        System.out.println("Result List: ");
        for (int i = 0; i < files.size(); i++) {
            System.out.printf("%d. %s\n", i, files.get(i));
        }
    }
}
