package net.bodz.swt.program;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.prefs.Preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.c.java.util.LocaleTraits;
import net.bodz.bas.cli.skel.BasicCLI;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.loader.boot.BootInfo;
import net.bodz.bas.meta.build.AppClassDoc;
import net.bodz.bas.meta.program.StartMode;
import net.bodz.bas.ui.UIException;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.ui.a.PreferredSize;
import net.bodz.swt.c.control.ControlAdapters;
import net.bodz.swt.c.control.Controls;
import net.bodz.swt.c.control.DynamicControl;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.c.menu.Menus;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.program.monitor.ThreadsMonitor;
import net.bodz.swt.reflect.AboutDialog;
import net.bodz.swt.reflect.CreditDialog;
import net.bodz.swt.reflect.SelectLanguageDialog;
import net.bodz.swt.reflect.util.DialogUI;

/**
 * @website http://www.bodz.net/products/BasicGUI
 */
@BootInfo(userlibs = { "bodz_swt", "bodz_icons" }, configs = SWTConfig.class)
@StartMode(StartMode.GUI)
@Language({ "en", "zh_CN" })
public class BasicGUI
        extends BasicCLI {

    /**
     * @option -Xw
     */
    private int shellWidth = SWT.DEFAULT;

    /**
     * @option -Xh
     */
    private int shellHeight = SWT.DEFAULT;

    {
        PreferredSize size = Ns.getN(getClass(), PreferredSize.class);
        if (size != null) {
            shellWidth = size.width();
            shellHeight = size.height();
        }
    }

    protected Shell shell;
    private DynamicControl viewArea;
    private Map<Object, Composite> views;

    protected UserInterface UI = new DialogUI();

    @Override
    public synchronized void execute(String... args)
            throws Exception {
        try {
            super.execute(args);
        } catch (net.bodz.bas.lang.Control c) {
            throw c;
        } catch (Exception e) {
            if (!shell.isDisposed()) {
                DialogUI iact = new DialogUI(shell);
                iact.alert(e.getMessage(), e);
            }
            throw e;
        }
    }

    @Override
    protected void _exit()
            throws Exception {
        checkHangOns();
        super._exit();
    }

    private static Set<String> ignoredThreads;
    static {
        ignoredThreads = new HashSet<String>();
        ignoredThreads.add("ReaderThread"); // JUnit thread
    }

    protected void checkHangOns() {
        int activeThreads = Thread.activeCount();
        Thread[] threads = new Thread[activeThreads];
        int n = Thread.enumerate(threads);
        int fg = 0;
        for (int i = 0; i < n; i++) {
            Thread t = threads[i];
            if (t.isDaemon())
                continue;
            String displayName = t.getName();
            if (ignoredThreads.contains(displayName))
                continue;
            fg++;
        }
        if (fg >= 2) {
            ThreadsMonitor monitor = new ThreadsMonitor(null, SWT.NONE);
            // monitor.setText("There stills running threads");
            monitor.open();
        }
    }

    private static final String KEY_LANG = "net.bodz.bas.preferredLanguage";

    @Override
    protected void doMain(String[] args)
            throws Exception {
        // this.args = args;
        views = new HashMap<Object, Composite>();

        final Display display = Display.getDefault();

        Class<?> appClass = getClass();
        Preferences preferences = Preferences.userNodeForPackage(appClass);
        String lang = preferences.get(KEY_LANG, null);
        if (lang == null) {
            SelectLanguageDialog dialog = new SelectLanguageDialog(null, SWT.NONE, getClass());
            String userChoice = dialog.open();
            if (userChoice != null) {
                lang = userChoice;
                preferences.put(KEY_LANG, lang);
            } else
                lang = Locale.getDefault().getLanguage();
        }
        Locale locale = LocaleTraits.parseLocale(lang);
        Locale.setDefault(locale);

        shell = createShell();// throws GUIExcaption
        UI = new DialogUI(shell);

        shell.open();
        shell.layout();

        _start();

        while (!shell.isDisposed())
            if (!display.readAndDispatch())
                display.sleep();
        _exit();
    }

    /**
     * shell created and just before message loop
     */
    protected void _start() {
    }

    private Image[] loadImages(URL[] urls)
            throws IOException {
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
        AppClassDoc classDoc = _loadClassInfo();
        String title = classDoc.getName();
        String doc = classDoc.getText().toPlainText();
        if (doc != null)
            title += ": " + doc;
        String version = classDoc.getVersion().toString();
        return title + " " + version;
    }

    protected Shell createShell()
            throws UIException {
        Shell shell = new Shell();
        shell.setText(getTitle());
        AppClassDoc classDoc = _loadClassInfo();
        Image[] icons;
        try {
            icons = loadImages(classDoc.getIcons());
        } catch (IOException e) {
            throw new UIException(e);
        }
        if (icons != null)
            shell.setImages(icons);

        Menu menu = createMenu(shell);
        if (menu != null)
            shell.setMenuBar(menu);

        shell.setLayout(new BorderLayout(0, 0));
        shell.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                // _exit();
            }
        });

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

        Point size = shell.computeSize(shellWidth, shellHeight);
        int width = shellWidth == SWT.DEFAULT ? size.x : shellWidth;
        int height = shellHeight == SWT.DEFAULT ? size.y : shellHeight;
        shell.setSize(width, height);
        Controls.center(shell);
        return shell;
    }

    protected Menu createMenu(Shell shell)
            throws UIException {
        boolean TODO = true;
        if (TODO)
            return null;
        final Menu menu = new Menu(shell, SWT.BAR);

        final Menu fileMenu = Menus.newSubMenu(menu, GUINLS.getString("BasicGUI.menu.file"));
        final MenuItem fileExit = new MenuItem(fileMenu, SWT.NONE);
        fileExit.setText(GUINLS.getString("BasicGUI.menu.exit"));

        return menu;
    }

    protected Menu createStartMenu(Shell shell)
            throws UIException {
        Menu menu = new Menu(shell, SWT.POP_UP);

        MenuItem about = new MenuItem(menu, SWT.PUSH);
        about.setText("About");
        about.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AboutDialog aboutDialog = new AboutDialog(getClass());
                aboutDialog.open();
            }
        });

        new MenuItem(menu, SWT.SEPARATOR);

        MenuItem pref = new MenuItem(menu, SWT.PUSH);
        pref.setText("Preferences...");

        MenuItem monitor = new MenuItem(menu, SWT.PUSH);
        monitor.setText("Threads Monitor...");
        monitor.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ThreadsMonitor monitor = new ThreadsMonitor(null, SWT.NONE);
                // monitor.setText("There stills running threads");
                monitor.open();
            }
        });

        new MenuItem(menu, SWT.SEPARATOR);

        MenuItem feature = new MenuItem(menu, SWT.PUSH);
        feature.setText("Feature Request...");
        ControlAdapters.openBrowser(feature, "http://track.bodz.net/request?product=?");

        MenuItem bug = new MenuItem(menu, SWT.PUSH);
        bug.setText("Bug Report...");
        ControlAdapters.openBrowser(bug, "http://track.bodz.net/bugs?product=?");

        MenuItem forum = new MenuItem(menu, SWT.PUSH);
        forum.setText("Forum");
        ControlAdapters.openBrowser(forum, "http://track.bodz.net/forum?product=?");

        new MenuItem(menu, SWT.SEPARATOR);

        MenuItem credits = new MenuItem(menu, SWT.PUSH);
        credits.setText("Credits...");
        credits.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CreditDialog creditDialog = new CreditDialog();
                creditDialog.open();
            }
        });

        return menu;
    }

    protected Control createToolBar(Composite parent)
            throws UIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
        return toolBar;
    }

    protected Control createStatusBar(Composite parent)
            throws UIException {
        Composite bottomBar = new Composite(parent, SWT.BORDER); // SWT.BORDER
        GridLayout bottomGrid = new GridLayout(3, false);
        bottomGrid.marginWidth = 0;
        bottomGrid.marginHeight = 0;
        bottomBar.setLayout(bottomGrid);

        final Menu startMenu = createStartMenu(parent.getShell());

        Image basLogo = SWTResources.getImageRes("/icons/bodz/bas_w.gif");
        Label startButton = new Label(bottomBar, SWT.NONE);
        startButton.setImage(basLogo);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                // startMenu.setLocation(e.x, e.y);
                startMenu.setVisible(true);
            }
        });

        String banner = getBannerString();
        Link link = new Link(bottomBar, SWT.NONE);
        link.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        link.setText(banner);
        link.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String s = e.text;
                URI uri;
                try {
                    uri = new URI(s);
                } catch (URISyntaxException ex) {
                    throw new IllegalUsageError(GUINLS.getString("BasicGUI.badBannerLink") + s, ex);
                }
                try {
                    Desktop.getDesktop().browse(uri);
                } catch (IOException ex) {
                    UI.alert(GUINLS.getString("BasicGUI.cantOpenBrowser"), ex);
                }
            }
        });

        Label updateTime = new Label(bottomBar, SWT.NONE);
        updateTime.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));
        updateTime.setAlignment(SWT.RIGHT);
        String dateString = _loadClassInfo().getDateString();
        if (dateString != null) {
            updateTime.setText(GUINLS.getString("BasicGUI.lastUpdated") + dateString);
        }
        return bottomBar;
    }

    protected String getBannerString() {
        AppClassDoc classDoc = _loadClassInfo();
        String author = classDoc.getAuthor().toString();
        String webSite = classDoc.getWebSite();
        if (webSite == null)
            webSite = "http://www.bodz.net/";
        String banner = GUINLS.format("BasicGUI.banner", author, webSite, webSite);
        return banner;
    }

    protected Control createExpandBar(Composite parent)
            throws UIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);
        return expandBar;
    }

    protected void createInitialView(Composite holder)
            throws UIException {
        holder.setLayout(new FillLayout());
        Label welcomeLabel = new Label(holder, SWT.NONE);
        welcomeLabel.setText(GUINLS.getString("BasicGUI.welcome"));
    }

    protected void createView(Composite holder, Object key)
            throws UIException {
        if (key == null) {
            createInitialView(holder);
            return;
        }
        throw new NotImplementedException("key: " + key);
    }

    protected void openView(Composite holder, Object key)
            throws UIException {
        Composite view = views.get(key);
        if (view == null) {
            view = new Composite(holder, SWT.NONE);
            createView(view, key);
            views.put(key, view);
        }
        viewArea.setControl(view);
    }

}
