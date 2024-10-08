package net.bodz.swt.c.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.CancelException;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.typer.std.ValidationException;
import net.bodz.bas.ui.event.IValidationListener;
import net.bodz.bas.ui.event.ValidationEvent;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.artifact.ArtifactDoc;
import net.bodz.swt.c.composite.EmptyComposite;
import net.bodz.swt.c.composite.Switcher;
import net.bodz.swt.c.control.Controls;
import net.bodz.swt.c.control.Mover;
import net.bodz.swt.c.resources.SWTResources;

public abstract class SimpleDialog
        extends Dialog
        implements
            II18nCapable {

    static final int _diagstyle = SWT.NONE; // SWT.BORDER;
    static final Point minSize = new Point(150, 30);

    private Shell parent;
    private String title;
    private Image icon;
    private Image image;

    private Shell shell;
    private Label imageLabel;
    private Composite detailBar;
    private Composite body;
    private Switcher errorBar;
    private Label errorLabel;
    private Composite userButtonBar;
    private Composite basicButtonBar;

    private SwtUserDialogs dialogs;

    private Object result;
    private boolean canceled;

    private List<IValidationListener> validationListeners;
    private List<SelectionListener> selectionListeners;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style) {
        this(parent, style, null);
    }

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style, String title) {
        super(parent == null ? new Shell() : parent, style);
        this.parent = parent;

        if (title == null) {
            ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(getClass());
            if (classDoc != null) {
                ArtifactDoc doc = new ArtifactDoc(classDoc);
                iString label = doc.getLabel();
                title = Nullables.toString(label);
            }
        }
        if (title != null) {
            setTitle(title);
            setText(title);
        }

        init();
    }

    void init() {
        icon = SWTResources.getImageRes("/icons/full/obj16/read_obj.gif");
        image = icon;
        dialogs = new SwtUserDialogs(parent, SWT.APPLICATION_MODAL);
    }

    /**
     * @throws CancelException
     */
    public abstract Object open();

    /**
     * @throws CancelException
     */
    protected synchronized Object open(boolean cancelException) {
        if (shell == null) {
            createContents();
            addEffects();
        }
        Controls.resizeToPreferredSize(shell, minSize, null);
        Mover.bind(shell).center();

        shell.layout();
        shell.open();

        Display display = Display.getCurrent(); // (parent != null ? parent :
        // shell).getDisplay();
        canceled = true;
        while (! shell.isDisposed()) {
            if (! display.readAndDispatch())
                display.sleep();
        }
        if (canceled)
            if (cancelException)
                throw new CancelException(nls.tr("user canceled"));
            else
                return null;
        return result;
    }

    public void addValidationListener(IValidationListener listener) {
        if (validationListeners == null)
            validationListeners = new ArrayList<IValidationListener>(1);
        validationListeners.add(listener);
    }

    public void removeValidationListener(IValidationListener listener) {
        if (validationListeners != null)
            validationListeners.remove(listener);
    }

    protected final void fireValidation()
            throws ValidationException {
        if (validationListeners != null) {
            ValidationEvent event = new ValidationEvent(this, result);
            for (IValidationListener listener : validationListeners)
                listener.validate(event);
        }
    }

    public void addSelectionListener(SelectionListener listener) {
        if (selectionListeners == null)
            selectionListeners = new ArrayList<SelectionListener>(1);
        selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        if (selectionListeners != null)
            selectionListeners.remove(listener);
    }

    protected final void fireSelection(SelectionEvent event) {
        if (selectionListeners != null)
            for (SelectionListener listener : selectionListeners)
                listener.widgetSelected(event);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null)
            title = nls.tr("(no title)");
        this.title = title;
        if (shell != null)
            shell.setText(title);
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        if (shell != null)
            shell.setImage(icon);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        if (imageLabel != null)
            imageLabel.setImage(image);
    }

    public Shell getShell() {
        return shell;
    }

    public Composite getBody() {
        return body;
    }

    public Composite getUserBar() {
        return userButtonBar;
    }

    private static final Object EVALUATE = new Object();
    private static final Object CANCEL = new Object();

    protected void accept() {
        accept(EVALUATE);
    }

    protected void accept(Object value) {
        if (value == CANCEL) {
            shell.dispose();
            return;
        }
        try {
            fireValidation();
        } catch (ValidationException e) {
            dialogs.alert(nls.tr("Check Failure"), e);
            return;
        }
        if (value == EVALUATE) {
            try {
                value = evaluate();
            } catch (Exception e) {
                dialogs.alert("Evaluation exception", e);
                return;
            }
        }
        result = value;
        shell.dispose();
        canceled = false;
    }

    protected Object evaluate()
            throws Exception {
        return true;
    }

    protected void cancel() {
        assert canceled;
        shell.dispose();
    }

    void createContents() {
        int style = SWT.APPLICATION_MODAL | SWT.TITLE | SWT.RESIZE | SWT.CLOSE;
        if (parent == null)
            shell = new Shell(Display.getCurrent(), style);
        else
            shell = new Shell(parent, style);

        GridLayout shellGrid = new GridLayout(1, false);
        shellGrid.marginWidth = 15;
        shellGrid.marginHeight = 10;
        shellGrid.verticalSpacing = 0;
        shell.setLayout(shellGrid);

        shell.setImage(getIcon());
        String title = getTitle();
        if (title != null)
            shell.setText(title);

        createTopBar(shell);

        body = new Composite(shell, _diagstyle);
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        body.setLayout(new FillLayout());

        errorBar = new Switcher(shell, _diagstyle);
        Composite errorComp = errorBar.get(true);
        errorComp.setLayout(new FillLayout());
        errorLabel = new Label(errorComp, _diagstyle);
        errorBar.set(false);

        final Composite bottomBar = new Composite(shell, _diagstyle);
        bottomBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final GridLayout bottomGrid = new GridLayout(2, false);
        bottomGrid.marginHeight = 0;
        bottomGrid.marginWidth = 0;
        bottomBar.setLayout(bottomGrid);

        userButtonBar = new Composite(bottomBar, _diagstyle);
        basicButtonBar = new Composite(bottomBar, _diagstyle);

        final GridData userData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        final GridData basicData = new GridData(SWT.RIGHT, SWT.CENTER, false, false);
        userButtonBar.setLayoutData(userData);
        basicButtonBar.setLayoutData(basicData);

        final RowLayout userLayout = new RowLayout(SWT.HORIZONTAL);
        userLayout.wrap = false;
        userLayout.marginLeft = 0;
        userLayout.marginRight = 0;
        userLayout.marginTop = 0;
        userLayout.marginBottom = 0;
        userLayout.pack = true; // pack user buttons.
        userButtonBar.setLayout(userLayout);

        final RowLayout basicLayout = new RowLayout(SWT.HORIZONTAL);
        basicLayout.wrap = false;
        basicLayout.marginLeft = 0;
        basicLayout.marginRight = 0;
        basicLayout.marginTop = 0;
        basicLayout.marginBottom = 0;
        basicLayout.pack = false; // don't pack basic buttons
        basicButtonBar.setLayout(basicLayout);

        if (detailBar != null)
            try {
                createDetail(detailBar);
            } catch (CreateException ex) {
                createException(detailBar, ex);
            }

        try {
            createBody(body);
        } catch (CreateException ex) {
            createException(body, ex);
        }

        try {
            createUserButtons(userButtonBar);
        } catch (CreateException ex) {
            createException(userButtonBar, ex);
        }

        try {
            createButtons(basicButtonBar);
        } catch (CreateException ex) {
            createException(basicButtonBar, ex);
        }
    }

    void createException(Composite parent, Exception e) {
        errorLabel.setText(nls.tr("ERROR: ") + e.toString());
        errorBar.set(true);
    }

    protected void createTopBar(Composite parent) {
        final Composite topBar = new Composite(shell, _diagstyle);
        topBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        GridLayout topGrid = new GridLayout(2, false);
        topGrid.marginWidth = 0;
        topGrid.marginHeight = 0;
        topBar.setLayout(topGrid);

        imageLabel = new Label(topBar, SWT.NONE);
        imageLabel.setImage(getImage());
        final GridData imageData = new GridData(GridData.BEGINNING, GridData.BEGINNING);
        imageLabel.setLayoutData(imageData);

        detailBar = new Composite(topBar, _diagstyle);
        GridData detailData = new GridData(GridData.FILL, GridData.FILL, true, true);
        detailBar.setLayoutData(detailData);
        detailBar.setLayout(new FillLayout());
    }

    protected void createDetail(Composite parent)
            throws CreateException {
        String title = getText();
        if (title != null) {
            Label titleLabel = new Label(parent, SWT.NONE);
            titleLabel.setText(title);
        }
    }

    protected void createBody(Composite parent)
            throws CreateException {
        new EmptyComposite(parent, _diagstyle);
    }

    protected void createUserButtons(Composite parent)
            throws CreateException {
        new EmptyComposite(parent, SWT.NONE);
    }

    protected void createButtons(Composite parent)
            throws CreateException {
        addOKButton(parent).setSelection(true);
        addCancelButton(parent);
    }

    /**
     * @param value
     *            {@value #NO_CHANGE} if the button won't change the current value.
     */
    protected Button addButton(Composite parent, int style, Image image, String text, final Object value) {
        final Button button = new Button(parent, style);
        if (image != null)
            button.setImage(image);
        if (text != null)
            button.setText(text);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                accept(value);
            }
        });
        return button;
    }

    protected Button addOKButton(Composite parent)
            throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources.getImageRes("/icons/full/obj16/translate.gif"), nls.tr("&OK"),
                EVALUATE);
    }

    protected Button addCancelButton(Composite parent)
            throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources.getImageRes("/icons/full/obj16/delete_obj.gif"),
                nls.tr("&Cancel"), CANCEL);
    }

    protected Button addYesButton(Composite parent)
            throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources.getImageRes("/icons/full/obj16/lrun_obj.gif"), nls.tr("&Yes"),
                true);
    }

    protected Button addNoButton(Composite parent)
            throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources.getImageRes("/icons/full/obj16/delete_obj.gif"), nls.tr("&No"),
                false);
    }

    protected void addEffects() {
    }

}
