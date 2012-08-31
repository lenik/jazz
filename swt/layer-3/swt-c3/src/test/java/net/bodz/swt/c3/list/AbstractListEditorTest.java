package net.bodz.swt.c3.list;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

import net.bodz.bas.err.CorruptedStateError;
import net.bodz.swt.c.test.WidgetTester;

public class AbstractListEditorTest
        extends WidgetTester {

    public AbstractListEditorTest() {
        super(false);
    }

    @Test
    public void test()
            throws Exception {
        List<File> files = new ArrayList<File>();
        String s = "entry";
        for (int i = 0; i < 20; i++) {
            files.add(new File(s));
            s += " *";
        }

        body.setLayout(new GridLayout(1, true));
        final Button allowArrangeCheck = new Button(body, SWT.CHECK);
        allowArrangeCheck.setText("&Allow arrange");

        final AbstractListEditor<File> filesEditor = new AbstractListEditor<File>(body, SWT.NONE) {
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

        filesEditor.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
        filesEditor.setBackground(display.getSystemColor(SWT.COLOR_BLUE));

        FontData fontData = filesEditor.getFont().getFontData()[0];
        fontData.setHeight(15);
        Font font = new Font(display, fontData);
        filesEditor.setFont(font);

        run();

        System.out.println("Result List: ");
        for (int i = 0; i < files.size(); i++) {
            System.out.printf("%d. %s\n", i, files.get(i));
        }
    }

}
