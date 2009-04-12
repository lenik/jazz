package net.bodz.swt.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.StartMode;
import net.bodz.bas.cli.BasicCLI;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.gui.GUIException;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.types.util.Ns;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.controls.util.Menus;
import net.bodz.swt.layouts.BorderLayout;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

@BootInfo(userlibs = { "bodz_swt", "bodz_icons" }, configs = SWTConfig.class)
@StartMode(StartMode.GUI)
public class BasicGUI extends BasicCLI {

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
    public synchronized void run(String... args) throws Throwable {
        try {
            super.run(args);
        } catch (Throwable t) {
            SWTInteraction iact = new SWTInteraction(shell);
            iact.alert(t.getMessage(), t);
            throw t;
        }
    }

    @Override
    protected void doMain(String[] args) throws Throwable {
        // this.args = args;
        views = new HashMap<Object, Composite>();

        final Display display = Display.getDefault();

        shell = createShell();// throws GUIExcaption
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

    private Image[] loadImages(URL[] urls) throws IOException {
        if (urls == null)
            return null;
        Image[] images = new Image[urls.length];
        for (int i = 0; i < urls.length; i++) {
            URL url = urls[i];
            images[i] = SWTResources.getImage(url);
        }
        return images;
    }

    protected String getTitle() {
        ClassInfo info = _loadClassInfo();
        String title = info.getName();
        String doc = info.getDoc();
        if (doc != null)
            title += ": " + doc; //$NON-NLS-1$
        String version = info.getVersionString();
        return title + " " + version; //$NON-NLS-1$
    }

    protected Shell createShell() throws GUIException, SWTException {
        Shell shell = new Shell();
        shell.setSize(shellWidth, shellHeight);
        Controls.center(shell);
        shell.setText(getTitle());
        ClassInfo info = _loadClassInfo();
        Image[] icons;
        try {
            icons = loadImages(info.getIcons());
        } catch (IOException e) {
            throw new GUIException(e);
        }
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

        Composite homeView = new Composite(viewArea, SWT.NONE);
        homeView.setLayout(new FillLayout());
        createInitialView(homeView);

        return shell;
    }

    protected Menu createMenu(Shell parent) throws SWTException, GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        final Menu menu = new Menu(parent, SWT.BAR);

        final Menu fileMenu = Menus.newSubMenu(menu, GUINLS
                .getString("BasicGUI.menu.file")); //$NON-NLS-1$
        final MenuItem fileExit = new MenuItem(fileMenu, SWT.NONE);
        fileExit.setText(GUINLS.getString("BasicGUI.menu.exit")); //$NON-NLS-1$

        return menu;
    }

    protected Control createToolBar(Composite parent) throws SWTException,
            GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
        return toolBar;
    }

    protected Control createStatusBar(Composite parent) throws SWTException,
            GUIException {
        Composite statusBar = new Composite(parent, SWT.BORDER); // SWT.BORDER
        statusBar.setLayout(new FillLayout());
        Label label = new Label(statusBar, SWT.NONE);
        String copyright = getCopyrightString();
        label.setText(copyright);
        return statusBar;
    }

    protected String getCopyrightString() {
        ClassInfo info = _loadClassInfo();
        String copyright = GUINLS.getString("BasicGUI.copyright") + info.getAuthor() + ", " //$NON-NLS-1$ //$NON-NLS-2$
                + info.getDateString();
        return copyright;
    }

    protected Control createExpandBar(Composite parent) throws SWTException,
            GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);
        return expandBar;
    }

    protected void createInitialView(Composite comp) throws GUIException,
            SWTException {
        comp.setLayout(new FillLayout());
        Label welcomeLabel = new Label(comp, SWT.NONE);
        welcomeLabel.setText(GUINLS.getString("BasicGUI.welcome")); //$NON-NLS-1$
    }

    protected void createView(Composite comp, Object key) throws SWTException,
            GUIException {
        if (key == null) {
            createInitialView(comp);
            return;
        }
        throw new NotImplementedException("key: " + key); //$NON-NLS-1$
    }

    protected void openView(Composite parent, Object key) throws SWTException,
            GUIException {
        Composite view = views.get(key);
        if (view == null) {
            view = new Composite(parent, SWT.NONE);
            createView(view, key);
            views.put(key, view);
        }
        viewArea.setControl(view);
    }

}
