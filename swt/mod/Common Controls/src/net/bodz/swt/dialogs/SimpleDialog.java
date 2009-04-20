package net.bodz.swt.dialogs;

import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.swt.controls.helper.EmptyComposite;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.gui.DialogInteraction;
import net.bodz.swt.nls.ControlsNLS;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public abstract class SimpleDialog extends Dialog implements Ref<Object> {

    static final int          _diagstyle = SWT.NONE;          // SWT.BORDER;
    static final Point        minSize    = new Point(150, 30);

    private Shell             parent;
    private String            title;
    private Image             icon;
    private Image             image;

    private Shell             shell;
    private Label             imageLabel;
    private Composite         detailBar;
    private Composite         body;
    private Composite         userBar;
    private Composite         basicBar;

    private DialogInteraction interact;

    private Object            result;
    private boolean           canceled;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style, String title) {
        super(parent == null ? new Shell() : parent, style);
        this.parent = parent;
        setTitle(title);
        if (title != null)
            setText(title);
        icon = SWTResources.getImageRes("/icons/full/obj16/read_obj.gif"); //$NON-NLS-1$
        image = icon;
        interact = new DialogInteraction(parent, SWT.APPLICATION_MODAL);
    }

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style) {
        this(parent, style, null);
    }

    public synchronized Object open() throws CancelException {
        if (shell == null) {
            createContents();
            addEffects();
        }
        Controls.resizeToPreferredSize(shell, minSize, null);
        Controls.center(shell);
        shell.layout();
        shell.open();

        Display display = (parent != null ? parent : shell).getDisplay();
        canceled = true;
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        if (canceled)
            throw new CancelException(ControlsNLS
                    .getString("SimpleDialog.userCanceled")); //$NON-NLS-1$
        return get();
    }

    public final Object get() {
        return result;
    }

    public final void set(Object result) {
        this.result = result;
    }

    protected void accept() {
        try {
            execute();
        } catch (CheckException ex) {
            interact.alert(
                    ControlsNLS.getString("SimpleDialog.checkFailure"), ex); //$NON-NLS-1$
            return;
        }
        shell.dispose();
        canceled = false;
    }

    protected void execute() throws CheckException {
    }

    void createContents() {
        int style = SWT.APPLICATION_MODAL | SWT.TITLE | SWT.RESIZE | SWT.CLOSE;
        if (parent == null)
            shell = new Shell(Display.getCurrent(), style);
        else
            shell = new Shell(parent, style);
        GridLayout shellGrid = new GridLayout();
        shellGrid.marginWidth = 15;
        shellGrid.marginHeight = 10;
        shell.setLayout(shellGrid);

        shell.setImage(getIcon());
        String title = getTitle();
        if (title != null)
            shell.setText(title);

        createTopBar(shell);

        body = new Composite(shell, SWT.NONE);
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        body.setLayout(new FillLayout());

        final Composite bottomBar = new Composite(shell, SWT.NONE);
        bottomBar
                .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final GridLayout bottomGrid = new GridLayout(2, false);
        bottomGrid.marginHeight = 0;
        bottomGrid.marginWidth = 0;
        bottomBar.setLayout(bottomGrid);

        userBar = new Composite(bottomBar, _diagstyle);
        basicBar = new Composite(bottomBar, _diagstyle);

        final GridData userData = new GridData(SWT.FILL, SWT.CENTER, true,
                false);
        final GridData basicData = new GridData(SWT.RIGHT, SWT.CENTER, false,
                false);
        userBar.setLayoutData(userData);
        basicBar.setLayoutData(basicData);

        final RowLayout userLayout = new RowLayout(SWT.HORIZONTAL);
        userLayout.wrap = false;
        userLayout.marginRight = 0;
        userLayout.marginLeft = 0;
        userLayout.pack = true; // pack user buttons.
        userBar.setLayout(userLayout);

        final RowLayout basicLayout = new RowLayout(SWT.HORIZONTAL);
        basicLayout.wrap = false;
        basicLayout.marginRight = 0;
        basicLayout.marginLeft = 0;
        basicLayout.pack = false; // don't pack basic buttons
        basicBar.setLayout(basicLayout);

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
            createUserButtons(userBar);
        } catch (CreateException ex) {
            createException(userBar, ex);
        }
        try {
            createButtons(basicBar);
        } catch (CreateException ex) {
            createException(basicBar, ex);
        }
    }

    void createException(Composite parent, Exception e) {
        Label errorLabel = new Label(parent, SWT.NONE);
        errorLabel
                .setText(ControlsNLS.getString("SimpleDialog.createError") + e.toString()); //$NON-NLS-1$
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
        final GridData imageData = new GridData(GridData.BEGINNING,
                GridData.BEGINNING);
        imageLabel.setLayoutData(imageData);
        detailBar = new Composite(topBar, _diagstyle);
        GridData detailData = new GridData(GridData.FILL, GridData.FILL, true,
                true);
        detailBar.setLayoutData(detailData);
        detailBar.setLayout(new FillLayout());
    }

    protected void createDetail(Composite parent) throws CreateException {
        String title = getText();
        if (title != null) {
            Label titleLabel = new Label(parent, SWT.NONE);
            titleLabel.setText(title);
        }
    }

    protected void createBody(Composite parent) throws CreateException {
        new EmptyComposite(parent, _diagstyle);
    }

    protected void createUserButtons(Composite parent) throws CreateException {
    }

    protected void createButtons(Composite parent) throws CreateException {
        addOKButton(parent);
        addCancelButton(parent);
    }

    protected static final Object NO_CHANGE = new Object();

    /**
     * @param value
     *            {@value #NO_CHANGE} if the button won't change the current
     *            value.
     */
    protected Button addButton(Composite parent, int style, Image image,
            String text, final Object value, final boolean dispose) {
        final Button button = new Button(parent, style);
        if (image != null)
            button.setImage(image);
        if (text != null)
            button.setText(text);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (value != NO_CHANGE)
                    set(value);
                if (dispose)
                    shell.dispose();
                else
                    accept();
            }
        });
        return button;
    }

    protected Button addOKButton(Composite parent) throws SWTException {
        Button okButton = addButton(parent, SWT.NONE, SWTResources
                .getImageRes("/icons/full/obj16/translate.gif"), //$NON-NLS-1$
                ControlsNLS.getString("SimpleDialog.ok"), //$NON-NLS-1$
                NO_CHANGE, false);
        okButton.setSelection(true);
        return okButton;
    }

    protected Button addCancelButton(Composite parent) throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources
                .getImageRes("/icons/full/obj16/delete_obj.gif"), //$NON-NLS-1$
                ControlsNLS.getString("SimpleDialog.cancel"), //$NON-NLS-1$
                NO_CHANGE, true);
    }

    protected Button addYesButton(Composite parent) throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources
                .getImageRes("/icons/full/obj16/lrun_obj.gif"), //$NON-NLS-1$
                ControlsNLS.getString("SimpleDialog.yes"), //$NON-NLS-1$
                true, false);
    }

    protected Button addNoButton(Composite parent) throws SWTException {
        return addButton(parent, SWT.NONE, SWTResources
                .getImageRes("/icons/full/obj16/delete_obj.gif"), //$NON-NLS-1$
                ControlsNLS.getString("SimpleDialog.no"), //$NON-NLS-1$
                false, false);
    }

    protected void addEffects() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null)
            title = "(no title)";
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
        return userBar;
    }

}
