package net.bodz.swt.gui.pfl;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.controls.helper.StackComposite;
import net.bodz.swt.gui.SWTInteraction;
import net.bodz.swt.gui.ValidateException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

import com.swtdesigner.SWTResourceManager;

/**
 * @TestBy WizardCompositeTest
 */
public class WizardComposite extends Composite implements PageChangeListener {

    private boolean              useLegend;
    private Composite            legends;
    private Label                titleImage;
    private Label                titleLabel;
    private StackComposite       contents;
    private Button               previousButton;
    private Button               nextButton;
    private Button               finishButton;

    private SymlinkPageFlow      pageFlow;
    private TextMap<PageFactory> pageFactories;

    private Interaction          iact;

    static boolean               showFinish = false;

    public WizardComposite(Composite parent, int style) {
        this(parent, style, false);
    }

    public WizardComposite(Composite parent, int style, boolean useLegend) {
        super(parent, style);
        this.useLegend = useLegend;
        pageFlow = new SymlinkPageFlow() {
            @Override
            public PageComposite loadPage(String path) {
                PageComposite page;
                Composite parent = WizardComposite.this.contents;
                if (pageFactories != null) {
                    PageFactory factory = pageFactories.get(path);
                    if (factory != null) {
                        page = factory.create(parent);
                        if (page != null)
                            return page;
                    }
                }
                page = createPage(parent, path);
                return page;
            }
        };
        pageFlow.addPageChangeListener(this);
        this.iact = new SWTInteraction(getShell());
        createContents();
    }

    public void definePage(String path, PageFactory factory) {
        if (pageFactories == null)
            pageFactories = new HashTextMap<PageFactory>();
        pageFactories.put(path, factory);
    }

    protected PageComposite createPage(Composite parent, String path) {
        return null;
    }

    @Override
    public void dispose() {
        pageFlow.removePageChangeListener(this);
        super.dispose();
    }

    Image defaultTitleImage;

    void createContents() {
        defaultTitleImage = SWTResourceManager.getImage(WizardComposite.class,
                "/icons/full/obj16/brkp_grp.gif");

        final BorderLayout borderLayout = new BorderLayout(0, 0);
        borderLayout.setVgap(3);
        setLayout(borderLayout);

        if (useLegend) {
            legends = new Composite(this, SWT.NONE);
            legends.setLayout(new FillLayout());
            legends.setLayoutData(BorderLayout.NORTH);
        }

        final Composite body = new Composite(this, SWT.NONE);
        final BorderLayout bodyLayout = new BorderLayout(0, 0);
        bodyLayout.setVgap(3);
        body.setLayout(bodyLayout);
        body.setLayoutData(BorderLayout.CENTER);

        final Composite titlebar = new Composite(body, SWT.NONE);
        titlebar.setLayout(new FormLayout());
        titlebar.setLayoutData(BorderLayout.NORTH);

        titleImage = new Label(titlebar, SWT.NONE);
        final FormData fd_titleImage = new FormData();
        fd_titleImage.top = new FormAttachment(0, 3);
        fd_titleImage.left = new FormAttachment(0, 3);
        titleImage.setLayoutData(fd_titleImage);
        titleImage.setImage(defaultTitleImage);

        titleLabel = new Label(titlebar, SWT.WRAP);
        final FormData fd_titleLabel = new FormData();
        fd_titleLabel.right = new FormAttachment(100, -5);
        fd_titleLabel.top = new FormAttachment(titleImage, 0, SWT.TOP);
        fd_titleLabel.left = new FormAttachment(titleImage, 5, SWT.RIGHT);
        titleLabel.setLayoutData(fd_titleLabel);
        titleLabel.setText("Title Of Current Page");

        contents = new StackComposite(body, SWT.BORDER);
        contents.setLayoutData(BorderLayout.CENTER);

        final Composite navbar1 = new Composite(this, SWT.NONE);
        navbar1.setLayout(new FormLayout());
        navbar1.setLayoutData(BorderLayout.SOUTH);

        final Composite navbar = new Composite(navbar1, SWT.NONE);
        final FormData fd_navbar = new FormData();
        fd_navbar.right = new FormAttachment(100, 0);
        fd_navbar.top = new FormAttachment(0, 0);
        navbar.setLayoutData(fd_navbar);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        gridLayout.makeColumnsEqualWidth = true;
        gridLayout.numColumns = 2;
        if (showFinish)
            gridLayout.numColumns++;
        navbar.setLayout(gridLayout);

        previousButton = new Button(navbar, SWT.NONE);
        previousButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
                false));
        previousButton.setText("&Previous");

        nextButton = new Button(navbar, SWT.NONE);
        nextButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
                false));
        nextButton.setText("&Next");

        if (showFinish) {
            finishButton = new Button(navbar, SWT.NONE);
            finishButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
                    false, false));
            finishButton.setText("&Finish");
        }

        setupEvents();
    }

    void setupEvents() {
        previousButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                pageFlow.previous();
            }
        });
        nextButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    pageFlow.next();
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
                    while (pageFlow.hasNext())
                        try {
                            pageFlow.next();
                        } catch (ValidateException ex) {
                            exceptionHandler(ex);
                            return;
                        }
                    Page lastPage = pageFlow.getCurrentPage();
                    assert lastPage != null;
                    lastPage.leave(null);
                    dispose();
                }
            });
    }

    /**
     * @see ControlAdapters#commit(Control, net.bodz.swt.adapters.CommitAdapter)
     */
    protected void exceptionHandler(ValidateException e) {
        iact.alert("Validate Exception", e);
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

    protected String getTitle() {
        return titleLabel.getText();
    }

    protected void setTitle(String title) {
        titleLabel.setText(title);
    }

    protected void setTitleImage(Image image) {
        titleImage.setImage(image);
    }

    @Override
    public void pageChange(PageChangeEvent e) {
        refreshPage();
    }

    void refreshPage() {
        Page currentPage = pageFlow.getCurrentPage();
        if (currentPage == null)
            return;
        String title = currentPage.getTitle();
        setTitle(title);
        Image image = currentPage.getImage();
        if (image == null)
            image = defaultTitleImage;
        setTitleImage(image);

        assert currentPage instanceof Composite;
        Composite currentPageComposite = (Composite) currentPage;
        contents.bringFront(currentPageComposite);

        previousButton.setEnabled(pageFlow.hasPrevious());
        nextButton.setEnabled(pageFlow.hasNext());
        if (finishButton != null)
            finishButton.setEnabled(!pageFlow.hasNext());
    }

}
