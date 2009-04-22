package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.ui.UserInterface;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.gui.DialogUI;
import net.bodz.swt.gui.ValidateException;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @test {@link WizardCompositeTest}
 */
public class WizardComposite extends Composite {

    private SymlinkPageFlow      pageFlow;
    private TextMap<PageFactory> pageFactories;

    private boolean              useLegend;
    private Composite            legends;

    private Composite            titleBar;
    private Image                defaultPageIcon;
    private Label                pageIconLabel;
    private Label                pageTitleLabel;

    private StackComposite       body;
    private PageComposite        emptyPage;

    private Composite            navBar;

    private static boolean       showBegin  = true;
    private static boolean       showFinish = false;
    private Button               beginButton;
    private Button               backButton;
    private Button               nextButton;
    private Button               finishButton;

    private final UserInterface  UI;

    public WizardComposite(Composite parent, int style) {
        this(parent, style, false);
    }

    public WizardComposite(Composite parent, int style, boolean useLegend) {
        super(parent, style);
        this.useLegend = useLegend;
        pageFlow = new SymlinkPageFlow() {
            @Override
            public boolean isPageLoadable(String address) {
                if (super.isPageLoadable(address))
                    return true;
                return WizardComposite.this.isPageDefined(address);
            }

            @Override
            public Page loadPage(String address) {
                Page page = super.loadPage(address);
                if (page == null)
                    page = WizardComposite.this.loadPage(address);
                return page;
            }
        };
        pageFlow.addLocationChangeListener(new LocationChangeListener() {
            @Override
            public void locationChange(LocationChangeEvent e) {
                refreshPage();
            }
        });
        this.UI = new DialogUI(getShell());
        createContents();
    }

    void createContents() {
        defaultPageIcon = SWTResources
                .getImageRes("/icons/full/obj16/brkp_grp.gif"); //$NON-NLS-1$

        final GridLayout gridLayout = new GridLayout(1, false);
        gridLayout.marginWidth = 5;
        gridLayout.marginHeight = 5;
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        setLayout(gridLayout);

        if (useLegend) {
            legends = new Composite(this, SWT.NONE);
            legends.setLayout(new FillLayout());
            final Label legendHr = new Label(this, SWT.SEPARATOR
                    | SWT.HORIZONTAL);
            legendHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
                    false));
        }

        titleBar = new Composite(this, SWT.NONE);
        final GridData titleData = new GridData(SWT.FILL, SWT.CENTER, true,
                false);
        titleBar.setLayoutData(titleData);
        final GridLayout titleLayout = new GridLayout(2, false);
        titleBar.setLayout(titleLayout);

        pageIconLabel = new Label(titleBar, SWT.NONE);
        pageIconLabel.setImage(defaultPageIcon);

        pageTitleLabel = new Label(titleBar, SWT.WRAP);
        pageTitleLabel.setText(GUINLS.getString("WizardComposite.pageTitle")); //$NON-NLS-1$
        pageTitleLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
                false));

        final Label titleHr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        titleHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        body = new StackComposite(this, SWT.NONE);
        final GridData bodyData = new GridData(SWT.FILL, SWT.FILL, true, true);
        body.setLayoutData(bodyData);

        emptyPage = new PageComposite(body, SWT.NONE);

        final Label navHr = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        navHr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final Composite navbar1 = new Composite(this, SWT.NONE);
        navbar1.setLayout(new FormLayout());
        navbar1.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, false));

        navBar = new Composite(navbar1, SWT.NONE);
        final RowLayout navLayout = new RowLayout();
        navBar.setLayout(navLayout);

        if (showBegin) {
            beginButton = new Button(navBar, SWT.NONE);
            beginButton.setImage(SWTResources
                    .getImageRes("/icons/full/etool16/shift_l_edit.gif")); //$NON-NLS-1$
            beginButton.setText(GUINLS.getString("WizardComposite.begin")); //$NON-NLS-1$
        }

        backButton = new Button(navBar, SWT.NONE);
        backButton.setImage(SWTResources
                .getImageRes("/icons/elcl16/nav_backward.gif")); //$NON-NLS-1$
        backButton.setText(GUINLS.getString("WizardComposite.back")); //$NON-NLS-1$

        nextButton = new Button(navBar, SWT.NONE);
        nextButton.setImage(SWTResources
                .getImageRes("/icons/elcl16/nav_forward.gif")); //$NON-NLS-1$
        nextButton.setText(GUINLS.getString("WizardComposite.next")); //$NON-NLS-1$

        if (showFinish) {
            finishButton = new Button(navBar, SWT.NONE);
            finishButton.setImage(SWTResources
                    .getImageRes("/icons/full/etool16/shift_r_edit.gif")); //$NON-NLS-1$
            finishButton.setText(GUINLS.getString("WizardComposite.finish")); //$NON-NLS-1$
        }

        setupEvents();
    }

    void setupEvents() {
        if (beginButton != null) {
            beginButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    while (pageFlow.has(-1))
                        pageFlow.goBack();
                }
            });
        }
        backButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                pageFlow.goBack();
            }
        });
        nextButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    if (pageFlow.canGoOn())
                        pageFlow.goOn();
                    else {
                        Page page = pageFlow.getPage();
                        String exitName = pageFlow.getPageNext(page);
                        String exitAddress = pageFlow.resolv(exitName);
                        pageFlow.goOn();
                        exit(exitAddress);
                    }
                } catch (ValidateException ex) {
                    exceptionHandler(ex);
                    return;
                }
            }
        });
        if (finishButton != null)
            finishButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    while (pageFlow.has(1))
                        try {
                            pageFlow.goOn();
                        } catch (ValidateException ex) {
                            exceptionHandler(ex);
                            return;
                        }
                    Page lastPage = pageFlow.getPage();
                    assert lastPage != null;
                    lastPage.leave(null, Location.QUIT);
                    dispose();
                }
            });
    }

    public Composite getLegends() {
        return legends;
    }

    public StackComposite getContents() {
        return body;
    }

    public void definePage(String address, PageFactory factory) {
        if (pageFactories == null)
            pageFactories = new HashTextMap<PageFactory>();
        pageFactories.put(address, factory);
    }

    /**
     * There may be page instances which isn't defined, but exist.
     * 
     * Override this method, and {@link #loadPage(String)} to implement dynamic
     * address pages.
     */
    public boolean isPageDefined(String address) {
        return pageFactories.containsKey(address);
    }

    protected PageComposite loadPage(String address) {
        if (pageFactories == null)
            return null;
        PageFactory factory = pageFactories.get(address);
        if (factory == null)
            return null;
        Composite parent = WizardComposite.this.body;
        final PageComposite page = factory.create(parent);
        page.addPageStateChangeListener(new PageStateChangeListener() {
            @Override
            public void pageStateChange(PageStateChangeEvent e) {
                if (page == pageFlow.getPage())
                    refreshPageState();
            }
        });
        return page;
    }

    /**
     * @see ControlAdapters#commit(Control, net.bodz.swt.adapters.CommitAdapter)
     */
    protected void exceptionHandler(ValidateException e) {
        if (!e.isSilent())
            UI.alert(GUINLS.getString("WizardComposite.validateException"), e); //$NON-NLS-1$
        Control control = e.getControl();
        if (control != null) {
            control.setFocus();
            if (control instanceof Text) {
                Text text = (Text) control;
                int len = text.getText().length();
                text.setSelection(0, len);
            }
        }
    }

    public SymlinkPageFlow getPageFlow() {
        return pageFlow;
    }

    protected void refreshPage() {
        Page page = pageFlow.getPage();
        String title = ""; //$NON-NLS-1$
        Image pageIcon = null;
        PageComposite pageComp = emptyPage;
        if (page != null) {
            title = page.getPageTitle();
            pageIcon = SWTResources.getImage(page.getPageIcon());
            assert page instanceof PageComposite;
            pageComp = (PageComposite) page;
        }
        pageTitleLabel.setText(title);
        if (pageIcon == null)
            pageIcon = defaultPageIcon;
        pageIconLabel.setImage(pageIcon);
        body.bringFront(pageComp);

        refreshPageState();

        body.layout();
    }

    protected void refreshPageState() {
        Page page = pageFlow.getPage();
        boolean locked = false;
        String nextLabel = ""; //$NON-NLS-1$
        if (page != null) {
            locked = page.isLocked();
            String nextName = pageFlow.getPageNext(page);
            nextLabel = toLabel(nextName);
        }
        beginButton.setEnabled(!locked && pageFlow.canGoBack());
        backButton.setEnabled(!locked && pageFlow.canGoBack());
        nextButton.setEnabled(!locked);
        if (finishButton != null)
            finishButton.setEnabled(!locked);

        nextButton.setText(nextLabel);

        // navbar.layout();
    }

    protected String toLabel(String name) {
        String label = GUINLS.getString("WizardComposite." + name); //$NON-NLS-1$
        if (label == null)
            label = "&" + Strings.ucfirst(name); //$NON-NLS-1$
        return label;
    }

    private List<WizardExitListener> exitListeners;

    protected void exit(String address) {
        if (exitListeners == null)
            return;
        WizardExitEvent e = new WizardExitEvent(this, address);
        for (WizardExitListener l : exitListeners)
            l.wizardExit(e);
    }

    public void addExitListener(WizardExitListener listener) {
        if (exitListeners == null)
            exitListeners = new ArrayList<WizardExitListener>();
        exitListeners.add(listener);
    }

    public void removeWizardExitListener(WizardExitListener listener) {
        if (exitListeners != null)
            exitListeners.remove(listener);
    }

}
