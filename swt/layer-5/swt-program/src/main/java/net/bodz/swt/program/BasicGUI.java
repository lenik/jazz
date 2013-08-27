package net.bodz.swt.program;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
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

import net.bodz.bas.c.java.util.LocaleTypers;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.gui.err.GUIException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.program.meta.StartMode;
import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;
import net.bodz.mda.xjdoc.model.javadoc.Author;
import net.bodz.swt.c.control.Controls;
import net.bodz.swt.c.control.DynamicControl;
import net.bodz.swt.c.layout.BorderLayout;
import net.bodz.swt.c.menu.Menus;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.c3.dialog.AboutDialog;
import net.bodz.swt.c3.dialog.CreditDialog;
import net.bodz.swt.c3.dialog.SelectLanguageDialog;
import net.bodz.swt.c3.dialog.SwtDialogs;
import net.bodz.swt.c3.dialog.ThreadsMonitor;

/**
 * @style width: 320px; height: 240px;
 * 
 * @lang en, zh-cn
 * @site.web http://www.bodz.net/products/BasicGUI
 */
@StartMode(StartMode.GUI)
public abstract class BasicGUI
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
//        TODO Set PreferredSize by CSS.
//        PreferredSize _size = getClass().getAnnotation(PreferredSize.class);
//        if (_size != null) {
//            shellWidth = _size.width();
//            shellHeight = _size.height();
//        }
    }

    protected Shell shell;
    private DynamicControl viewArea;
    private Map<Object, Composite> views;

    protected IUserDialogs userDialogs = new SwtDialogs();

    @Override
    public synchronized void execute(String... args)
            throws Exception {
        try {
            super.execute(args);
        } catch (Exception e) {
            if (shell != null && !shell.isDisposed()) {
                SwtDialogs iact = new SwtDialogs(shell);
                iact.alert(e.getMessage(), e);
            } else {
                // TODO logger.error
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
            String label = t.getName();
            if (ignoredThreads.contains(label))
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
    protected void mainImpl(String... args)
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
        Locale locale = LocaleTypers.parseLocale(lang);
        Locale.setDefault(locale);

        shell = createShell();// throws GUIExcaption
        userDialogs = new SwtDialogs(shell);

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
        ArtifactDoc artifactDoc = getXjdoc();
        String title = artifactDoc.getName();
        iString doc = artifactDoc.getText();
        if (doc != null)
            title += ": " + doc;
        IVersion version = artifactDoc.getVersion();
        return title + " " + version;
    }

    protected Shell createShell()
            throws GUIException {
        Shell shell = new Shell();
        shell.setText(getTitle());
        ArtifactDoc artifactDoc = getXjdoc();
        
        // TODO Set icon by CSS.
//        Image[] icons;
//        try {
//            icons = loadImages(artifactDoc.getIcons());
//        } catch (IOException e) {
//            throw new GUIException(e);
//        }
//        if (icons != null)
//            shell.setImages(icons);

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
            throws GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        final Menu menu = new Menu(shell, SWT.BAR);

        final Menu fileMenu = Menus.newSubMenu(menu, tr._("&File"));
        final MenuItem fileExit = new MenuItem(fileMenu, SWT.NONE);
        fileExit.setText(tr._("E&xit"));

        return menu;
    }

    protected Menu createStartMenu(Shell shell)
            throws GUIException {
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
        ControlAdapters.onclickBrowse(feature, "http://track.bodz.net/request?product=?");

        MenuItem bug = new MenuItem(menu, SWT.PUSH);
        bug.setText("Bug Report...");
        ControlAdapters.onclickBrowse(bug, "http://track.bodz.net/bugs?product=?");

        MenuItem forum = new MenuItem(menu, SWT.PUSH);
        forum.setText("Forum");
        ControlAdapters.onclickBrowse(forum, "http://track.bodz.net/forum?product=?");

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
            throws GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
        return toolBar;
    }

    protected Control createStatusBar(Composite parent)
            throws GUIException {
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
                    throw new IllegalUsageError(tr._("Bad banner link URL: ") + s, ex);
                }
                try {
                    Desktop.getDesktop().browse(uri);
                } catch (IOException ex) {
                    userDialogs.alert(tr._("Can\'t open browser"), ex);
                }
            }
        });

        Label updateTime = new Label(bottomBar, SWT.NONE);
        updateTime.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));
        updateTime.setAlignment(SWT.RIGHT);
        String dateString = getXjdoc().getReleaseDateString();
        if (dateString != null) {
            updateTime.setText(tr._("Last updated: ") + dateString);
        }
        return bottomBar;
    }

    // TODO Predefined URL constants..
    static URL WWW_BODZ_NET;
    static {
        try {
            WWW_BODZ_NET = new URL("http://www.bodz.net/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e); 
        }
    }
    
    protected String getBannerString() {
        ArtifactDoc artifactDoc = getXjdoc();
        Author author = artifactDoc.getAuthor();
        URL site = artifactDoc.getSiteLink();
        if (site == null)
            site = WWW_BODZ_NET;
        String banner = GUINLS.format("BasicGUI.banner", author, site, site);
        return banner;
    }

    protected Control createExpandBar(Composite parent)
            throws GUIException {
        boolean TODO = true;
        if (TODO)
            return null;
        ExpandBar expandBar = new ExpandBar(parent, SWT.NONE);
        return expandBar;
    }

    protected void createInitialView(Composite holder)
            throws GUIException {
        holder.setLayout(new FillLayout());
        Label welcomeLabel = new Label(holder, SWT.NONE);
        welcomeLabel.setText(tr._("Welcome BasicGUI!"));
    }

    protected void createView(Composite holder, Object key)
            throws GUIException {
        if (key == null) {
            createInitialView(holder);
            return;
        }
        throw new NotImplementedException("key: " + key);
    }

    protected void openView(Composite holder, Object key)
            throws GUIException {
        Composite view = views.get(key);
        if (view == null) {
            view = new Composite(holder, SWT.NONE);
            createView(view, key);
            views.put(key, view);
        }
        viewArea.setControl(view);
    }

}
