package net.bodz.swt.c3.pageflow;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.gui.ia.IUserInteraction;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.variant.map.SimpleRequest;
import net.bodz.swt.c.control.ControlAdapters;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.c3.ia.DialogInteraction;

public class NavigatorComposite
        extends Composite
        implements IPageContext, II18nCapable {

    private static final int MAX_HISTORY = 300;

    static final int SHOW_BEGIN = 1;
    static final int SHOW_BACK = 2;
    static final int SHOW_FORWARD = 4;
    static final int SHOW_END = 8;

    class DefaultPageFlow
            extends AbstractPageFlow {

        public DefaultPageFlow() {
            super(NavigatorComposite.this);
        }

        @Override
        protected void showTurn(TreePath prev, TreePath path)
                throws PageException {
            if (prev != null) {
                IPage prevPage = book.getPage(prev);
                assert prevPage != null;
                assert prevPage == pageContainer.getActivePage();
                prevPage.removePropertyChangeListener(refresh);
            }
            showPage(path);
            if (path != null) {
                IPage activePage = book.getPage(path);
                assert activePage != null;
                assert activePage == pageContainer.getActivePage();
                activePage.addPropertyChangeListener(refresh);
            }
        }

        @Override
        protected void handleException(Exception e) {
            UI.alert(tr._("Validate Exception"), e);
        }

    }

    private IBook book;
    private NLS dict;
    private History history;
    private AbstractPageFlow pageFlow;

    private IUserInteraction UI;

    private boolean useLegend;
    private Composite legends;

    private Composite titleBar;
    private Image defaultPageIcon;
    private Label pageIconLabel;
    private Label pageTitleLabel;

    private PageContainer pageContainer;

    private Composite bottomBar;
    private Composite navBar;
    private Composite methodsBar;

    private int flags = SHOW_BACK | SHOW_FORWARD;

    private Button goBeginButton;
    private Button goBackButton;
    private Button goForwardButton;
    private Button goEndButton;

    private final String labelFormat_sc;

    public NavigatorComposite(IBook book, Composite parent, int style) {
        this(book, new History(MAX_HISTORY), parent, style);
    }

    public NavigatorComposite(IBook book, History history, Composite parent, int style) {
        super(parent, style);
        if (book == null)
            throw new NullPointerException("book");
        if (history == null)
            throw new NullPointerException("history");
        this.book = book;
        this.dict = book.getDict();
        this.history = history;
        this.pageFlow = new DefaultPageFlow();
        this.UI = new DialogInteraction(getShell());

        labelFormat_sc = tr._("%s");

        createContents();
    }

    @Override
    public IBook getBook() {
        return book;
    }

    @Override
    public History getHistory() {
        return history;
    }

    @Override
    public AbstractPageFlow getPageFlow() {
        return pageFlow;
    }

    void createContents() {
        defaultPageIcon = SWTResources.getImageRes("/icons/full/obj16/brkp_grp.gif");

        final GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.marginWidth = 5;
        gridLayout.marginHeight = 5;
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        setLayout(gridLayout);

        if (useLegend) {
            legends = new Composite(this, SWT.NONE);
            legends.setLayout(new FillLayout());
            final Label legendHr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
            legendHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        }

        titleBar = new Composite(this, SWT.NONE);
        final GridData titleData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        titleBar.setLayoutData(titleData);
        final GridLayout titleLayout = new GridLayout(2, false);
        titleBar.setLayout(titleLayout);

        pageIconLabel = new Label(titleBar, SWT.NONE);
        pageIconLabel.setImage(defaultPageIcon);

        pageTitleLabel = new Label(titleBar, SWT.WRAP);
        pageTitleLabel.setText(tr._("Title Of Current Page"));
        pageTitleLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final Label titleHr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        titleHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        pageContainer = new PageContainer(book, this, SWT.NONE);
        pageContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        final Label navHr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        navHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        bottomBar = new Composite(this, SWT.NONE);
        final GridLayout gridLayout_bottom = new GridLayout();
        gridLayout_bottom.horizontalSpacing = 0;
        gridLayout_bottom.numColumns = 2;
        gridLayout_bottom.verticalSpacing = 0;
        gridLayout_bottom.marginWidth = 0;
        gridLayout_bottom.marginHeight = 0;
        bottomBar.setLayout(gridLayout_bottom);
        bottomBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        navBar = new Composite(bottomBar, SWT.NONE);
        final RowLayout navLayout = new RowLayout();
        navBar.setLayout(navLayout);

        if ((flags & SHOW_BEGIN) != 0) {
            goBeginButton = new Button(navBar, SWT.NONE);
            goBeginButton.setImage(SWTResources.getImageRes("/icons/full/etool16/shift_l_edit.gif"));
            goBeginButton.setText(tr._("Beg&in"));
        }

        if ((flags & SHOW_BACK) != 0) {
            goBackButton = new Button(navBar, SWT.NONE);
            goBackButton.setImage(SWTResources.getImageRes("/icons/elcl16/nav_backward.gif"));
            goBackButton.setText(tr._("&Back"));
        }

        if ((flags & SHOW_FORWARD) != 0) {
            goForwardButton = new Button(navBar, SWT.NONE);
            goForwardButton.setImage(SWTResources.getImageRes("/icons/elcl16/nav_forward.gif"));
            goForwardButton.setText(tr._("&Next"));
        }

        if ((flags & SHOW_END) != 0) {
            goEndButton = new Button(navBar, SWT.NONE);
            goEndButton.setImage(SWTResources.getImageRes("/icons/full/etool16/shift_r_edit.gif"));
            goEndButton.setText(tr._("&Finish"));
        }

        setupEvents();

        TreePath initPath = book.getFirst();
        if (initPath != null)
            pageFlow.go(initPath);
    }

    void setupEvents() {
        if (goBeginButton != null)
            goBeginButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    int backwards = history.getBackwards();
                    pageFlow.go(-backwards);
                }
            });

        if (goBackButton != null)
            goBackButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    pageFlow.go(-1);
                }
            });
        if (goForwardButton != null)
            goForwardButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    pageFlow.go(1);
                }
            });
        if (goEndButton != null)
            goEndButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    int forwards = history.getForwards();
                    pageFlow.go(forwards);
                }
            });
        history.addHistoryChangeListener(new HistoryChangeListener() {
            @Override
            public void historyChange(HistoryChangeEvent e) {
                refresh.refreshNav();
            }
        });
        history.addHistoryRemovedListener(new HistoryRemovedListener() {
            @Override
            public void historyRemoved(HistoryRemovedEvent event) {
                if (event.getRefCount() == 0) {
                    TreePath path = event.getPath();
                    // Log page drops...
                    pageContainer.remove(path);
                }
            }
        });
    }

    class RefreshAdapter
            implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String name = evt.getPropertyName();
            if (IPage.PROP_PAGEICON.equals(name))
                refreshIcon();
            else if (IPage.PROP_PAGETITLE.equals(name))
                refreshTitle();
            else if (IPage.PROP_METHODS.equals(name))
                refreshMethods();
            else if (IPage.PROP_STICKED.equals(name)) {
                refreshNav();
                refreshMethods();
            }
        }

        void refreshIcon() {
            IPage page = pageContainer.getActivePage();
            Image pageIcon = null;
            if (page != null)
                pageIcon = SWTResources.getImage(page.getPageIcon());
            if (pageIcon == null)
                pageIcon = defaultPageIcon;
            pageIconLabel.setImage(pageIcon);
        }

        void refreshTitle() {
            IPage page = pageContainer.getActivePage();
            String title = "";
            if (page != null)
                title = page.getPageTitle();
            pageTitleLabel.setText(title);
        }

        void refreshNav() {
            IPage page = pageContainer.getActivePage();
            boolean locked = false;
            if (page != null)
                locked = page.isSticked();
            if (goBeginButton != null)
                goBeginButton.setEnabled(!locked && history.has(-1));
            if (goBackButton != null)
                goBackButton.setEnabled(!locked && history.has(-1));
            if (goForwardButton != null)
                goForwardButton.setEnabled(!locked && history.has(1));
            if (goEndButton != null)
                goEndButton.setEnabled(!locked && history.has(1));
        }

        void refreshMethods() {
            TreePath path = pageContainer.getActivePath();
            IPage page = pageContainer.getActivePage();
            boolean locked = false;
            if (page != null)
                locked = page.isSticked();

            // recreate method bar.
            if (methodsBar != null) {
                methodsBar.dispose();
                methodsBar = null;
            }
            final Composite _bar = new Composite(bottomBar, SWT.NONE);
            _bar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
            _bar.setLayout(new RowLayout());
            ControlAdapters.cascadeDispose(_bar);
            methodsBar = _bar;

            if (page != null)
                createMethodButtons(methodsBar, path, page.getMethods(), locked);
            createMethodButtons(methodsBar, path, book.getMethods(), locked);

            bottomBar.layout();
        }

    }

    RefreshAdapter refresh = new RefreshAdapter();

    void showPage(final TreePath path)
            throws PageException {
        pageContainer.go(path); // Ex.
        refresh.refreshIcon();
        refresh.refreshTitle();
        refresh.refreshMethods();
    }

    void createMethodButtons(Composite bar, TreePath contextPath, Collection<PageMethod> methods, boolean disabled) {
        if (methods != null) {
            int index = 0;
            for (final PageMethod method : methods) {
                if (method == null)
                    throw new NullPointerException("method[" + index + "]");
                index++;

                ImageData image = method.getImage();
                String label = method.getLabel();

                Button button = new Button(bar, SWT.NONE);
                button.setEnabled(!disabled);
                if (image != null)
                    button.setImage(SWTResources.getImage(image));
                label = formatLabel(label);
                button.setText(label);
                button.setToolTipText(method.getToolTip());

                String action = method.getAction();
                final SimpleRequest request = new SimpleRequest(this, contextPath, action);
                button.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        method.widgetSelected(e);
                        if (e.doit)
                            pageFlow.submit(request);
                    }
                });
            }
        }
    }

    String formatLabel(String label) {
        if (label == null)
            label = "(?)";

        // adjust underline
        int amp = label.indexOf('&');
        char k = 0;
        if (amp != -1) {
            k = label.charAt(amp);
            label = label.substring(0, amp) + label.substring(amp + 1);
        } else if (!label.isEmpty()) {
            amp = 0;
            k = label.charAt(0);
        }

        boolean inline = !labelFormat_sc.contains("&");
        label = dict.tr(label);
        if (inline) {
            int insertAmp = label.indexOf(k);
            if (insertAmp != -1) {
                label = label.substring(0, insertAmp) + "&" + label.substring(insertAmp);
                return label;
            }
        }
        label = String.format(labelFormat_sc, label, Character.toUpperCase(k));
        return label;
    }

}
