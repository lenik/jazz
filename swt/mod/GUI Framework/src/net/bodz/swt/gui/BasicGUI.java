package net.bodz.swt.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.cli.a.RunInfo;
import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Types;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.gui.util.Controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import swing2swt.layout.BorderLayout;

@RunInfo(lib = { "bodz_swt", "bodz_icons" },

load = { "findcp eclipse*/plugins/org.eclipse.swt_*", })
public class BasicGUI extends BasicCLI {

    static {
        Types.load(GUIConfig.class);
    }

    @Option(alias = "Xw")
    private int                    shellWidth  = 320;

    @Option(alias = "Xh")
    private int                    shellHeight = 240;

    {
        PreferredSize size = Ns.getN(getClass(), PreferredSize.class);
        if (size != null) {
            shellWidth = size.width();
            shellHeight = size.height();
        }
    }

    protected Shell                shell;
    private DynamicControl         viewArea;
    private Map<Object, Composite> views;

    @Override
    protected void doMain(String[] args) throws Throwable {
        // this.args = args;
        views = new HashMap<Object, Composite>();

        final Display display = Display.getDefault();
        shell = createShell();
        shell.open();
        shell.layout();
        _start();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    /**
     * shell created and just before message loop
     */
    protected void _start() {
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

    protected Shell createShell() throws GUIException, SWTException {
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

        Control toolBar = createToolBar(shell);
        if (toolBar != null)
            toolBar.setLayoutData(BorderLayout.NORTH);

        Control statusBar = createStatusBar(shell);
        if (statusBar != null)
            statusBar.setLayoutData(BorderLayout.SOUTH);

        Control expandBar = createExpandBar(shell);
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

    protected Control createToolBar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
        return toolBar;
    }

    protected Control createStatusBar(Composite parent) {
        Composite statusBar = new Composite(parent, SWT.NONE); // SWT.BORDER
        return statusBar;
    }

    protected Control createExpandBar(Composite parent) {
        ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);
        return expandBar;
    }

    protected Composite createInitialView(Composite parent)
            throws GUIException, SWTException {
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
