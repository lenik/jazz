package net.bodz.swt.dialogs;

import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.swt.controls.helper.EmptyComposite;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.gui.SWTInteraction;
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

    static final int       _diagstyle = SWT.NONE;          // SWT.BORDER;
    static final Point     minSize    = new Point(150, 30);

    private Shell          parent;

    private Shell          shell;
    private Composite      detailBar;
    private Composite      body;
    private Composite      userBar;
    private Composite      basicBar;

    private SWTInteraction interact;

    private Object         result;
    private boolean        canceled;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style, String text) {
        super(parent == null ? new Shell() : parent, style);
        this.parent = parent;
        if (text == null)
            text = "(no title)";
        setText(text);
        interact = new SWTInteraction(parent, SWT.APPLICATION_MODAL);
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

    public Object get() {
        return result;
    }

    public void set(Object result) {
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
        String title = getText();
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

        final Label imageLabel = new Label(topBar, SWT.NONE);
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

    protected Button addOKButton(Composite parent) throws SWTException {
        final Button okButton = new Button(parent, SWT.NONE);
        okButton.setSelection(true);
        okButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/translate.gif")); //$NON-NLS-1$
        okButton.setText(ControlsNLS.getString("SimpleDialog.ok")); //$NON-NLS-1$
        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                accept();
            }
        });
        return okButton;
    }

    protected Button addCancelButton(Composite parent) throws SWTException {
        final Button cancelButton = new Button(parent, SWT.NONE);
        // rd_okButton.height = buttonHeight;
        cancelButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/delete_obj.gif")); //$NON-NLS-1$
        cancelButton.setText(ControlsNLS.getString("SimpleDialog.cancel")); //$NON-NLS-1$
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.dispose();
            }
        });
        return cancelButton;
    }

    protected Button addYesButton(Composite parent) throws SWTException {
        final Button yesButton = new Button(parent, SWT.NONE);
        yesButton.setSelection(true);
        yesButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/lrun_obj.gif")); //$NON-NLS-1$
        yesButton.setText(ControlsNLS.getString("SimpleDialog.yes")); //$NON-NLS-1$
        yesButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                set(true);
                accept();
            }
        });
        return yesButton;
    }

    protected Button addNoButton(Composite parent) throws SWTException {
        final Button noButton = new Button(parent, SWT.NONE);
        noButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/delete_obj.gif")); //$NON-NLS-1$
        noButton.setText(ControlsNLS.getString("SimpleDialog.no")); //$NON-NLS-1$
        noButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                set(false);
                accept();
            }
        });
        return noButton;
    }

    protected void addEffects() {
    }

    public Shell getShell() {
        return shell;
    }

    protected Image getIcon() {
        return getImage();
    }

    protected Image getImage() {
        return SWTResources.getImageRes("/icons/full/obj16/read_obj.gif"); //$NON-NLS-1$
    }

    public Composite getBody() {
        return body;
    }

    public Composite getUserBar() {
        return userBar;
    }

}
