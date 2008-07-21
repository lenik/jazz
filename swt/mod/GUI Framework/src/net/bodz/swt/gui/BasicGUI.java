package net.bodz.swt.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.annotations.ClassInfo;
import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.cli.Option;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.gui.util.Controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import swing2swt.layout.BorderLayout;

public class BasicGUI extends BasicCLI {

    @Option(alias = "Xw")
    private int                    shellWidth  = 320;

    @Option(alias = "Xh")
    private int                    shellHeight = 240;

    protected Shell                shell;
    private DynamicControl         viewArea;
    private Map<Object, Composite> views;

    @Override
    protected void _main(String[] args) throws Throwable {
        // this.args = args;
        views = new HashMap<Object, Composite>();

        final Display display = Display.getDefault();
        shell = createShell();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    private Image[] loadImages(URL[] urls) {
        if (urls == null)
            return null;
        Image[] images = new Image[urls.length];
        for (int i = 0; i < urls.length; i++) {
            images[i] = null;
        }
        return images;
    }

    protected Shell createShell() {
        Shell shell = new Shell();
        shell.setSize(shellWidth, shellHeight);
        ClassInfo info = _loadClassInfo();
        shell.setText(info.getDoc());
        Image[] icons = loadImages(info.getIcons());
        if (icons != null)
            shell.setImages(icons);

        Menu menu = createMenu(shell);
        if (menu != null)
            shell.setMenuBar(menu);

        shell.setLayout(new BorderLayout(0, 0));

        Composite toolBar = createToolBar(shell);
        if (toolBar != null)
            toolBar.setLayoutData(BorderLayout.NORTH);

        Composite statusBar = createStatusBar(shell);
        if (statusBar != null)
            statusBar.setLayoutData(BorderLayout.SOUTH);

        Composite expandBar = createExpandBar(shell);
        if (expandBar != null)
            expandBar.setLayoutData(BorderLayout.WEST);

        viewArea = new DynamicControl(shell, SWT.NONE);
        viewArea.setLayoutData(BorderLayout.CENTER);

        Composite initialView = createInitialView(viewArea);
        if (initialView != null)
            initialView.setLayoutData(viewArea);
        viewArea.setControl(initialView);

        return shell;
    }

    protected Menu createMenu(Shell parent) {
        final Menu menu = new Menu(parent, SWT.BAR);

        final Menu fileMenu = Controls.newSubMenu(menu, "&File");
        final MenuItem fileExit = new MenuItem(fileMenu, SWT.NONE);
        fileExit.setText("E&xit");

        return menu;
    }

    protected Composite createToolBar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
        return toolBar;
    }

    protected Composite createStatusBar(Composite parent) {
        Composite statusBar = new Composite(parent, SWT.NONE); // SWT.BORDER
        return statusBar;
    }

    protected Composite createExpandBar(Composite parent) {
        ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);
        return expandBar;
    }

    protected Composite createInitialView(Composite parent) {
        return createView(parent, null);
    }

    protected Composite createView(Composite parent, Object key) {
        Composite view = new Composite(parent, SWT.NONE);
        return view;
    }

    protected void openView(Composite parent, Object key) {
        Composite view = views.get(key);
        if (view == null) {
            view = createView(parent, key);
            views.put(key, view);
        }
        viewArea.setControl(view);
    }

}
