package net.bodz.swt.c.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.swt.c.control.Controls;
import net.bodz.swt.c.control.Mover;
import net.bodz.swt.c.layout.BorderLayout;

public abstract class WidgetTester
        extends Assert
        implements II18nCapable {

    boolean autorun = true;
    String title;

    protected Display display = Display.getDefault();
    protected Shell shell;

    protected Composite body;
    protected Composite toolbar;

    public WidgetTester() {
        this(true);
    }

    public WidgetTester(boolean junitMode) {
        title = getClass().getSimpleName();
        // title = tr._("Control Test App");
        if (!junitMode) {
            create();
            boot();
        }
    }

    @Before
    public void create() {
        shell = new Shell();
        shell.setSize(320, 240);
        shell.setLayout(new BorderLayout());
        shell.setText(title);

        toolbar = new Composite(shell, SWT.NONE);
        toolbar.setLayoutData(BorderLayout.NORTH);
        toolbar.setLayout(new FillLayout());

        body = new Composite(shell, SWT.NONE);
        body.setLayoutData(BorderLayout.CENTER);

        FillLayout bodyLayout = new FillLayout();
        bodyLayout.marginWidth = 5;
        bodyLayout.marginHeight = 5;
        body.setLayout(bodyLayout);

        createTools();
        createBody();
    }

    @After
    public void boot() {
        if (autorun)
            run();
    }

    public boolean isAutorun() {
        return autorun;
    }

    public void setAutorun(boolean autorun) {
        this.autorun = autorun;
    }

    public void addToolButton(String text, SelectionListener listener) {
        Button button = new Button(toolbar, SWT.NONE);
        button.setText(text);
        button.addSelectionListener(listener);
    }

    protected void createTools() {
        addToolButton(tr._("Auto &Fit"), //
                new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        autoFit();
                    }
                });
    }

    public void autoFit() {
        Controls.resizeToPreferredSize(shell);
        Mover.bind(shell).bottomLeftToCenter();
    }

    protected void createBody() {
    }

    public void run() {
        shell.layout();
        Mover.bind(shell).bottomLeftToCenter();

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

}
