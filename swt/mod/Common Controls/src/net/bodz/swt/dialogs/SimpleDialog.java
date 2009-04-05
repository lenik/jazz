package net.bodz.swt.dialogs;

import net.bodz.bas.lang.err.CancelException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.ref.Ref;
import net.bodz.swt.controls.helper.FixSizeComposite;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.gui.SWTInteraction;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.SWTResourceManager;

public class SimpleDialog extends Dialog implements Ref<Object> {

    protected Shell     parent;

    /**
     * no title bar if title is null
     */
    private String      title;
    private int         margin       = 20;
    private int         padding      = 5;
    private int         buttonHeight = 30;

    protected Shell     shell;
    protected Composite body;

    private Object      result;
    private boolean     canceled;

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style, String title) {
        super(parent == null ? new Shell() : parent, style);
        this.title = title;
    }

    /**
     * @see SWT#PRIMARY_MODAL
     * @see SWT#APPLICATION_MODAL
     * @see SWT#SYSTEM_MODAL
     */
    public SimpleDialog(Shell parent, int style) {
        this(parent, style, null);
    }

    protected String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private static final int minWidth  = 240;
    private static final int minHeight = 30;

    public synchronized Object open() throws CancelException {
        if (shell == null)
            try {
                createContents();
            } catch (SWTException e) {
                throw new RuntimeException(e);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        size.x = Math.max(size.x, minWidth);
        size.y = Math.max(size.y, minHeight);
        shell.setSize(size);
        Controls.center(shell);
        shell.layout();

        boolean center = true;
        if (center) {
            Rectangle outer;
            if (parent != null)
                outer = parent.getBounds();
            else
                outer = shell.getDisplay().getBounds();
            Rectangle inner = shell.getBounds();

            inner.x = outer.x + (outer.width - inner.width) / 2;
            inner.y = outer.y + (outer.height - inner.height) / 2;
            shell.setBounds(inner);
        }

        addEffects();

        shell.open();

        Display display = (parent != null ? parent : shell).getDisplay();
        canceled = true;
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        if (canceled)
            throw new CancelException("user canceled");
        return get();
    }

    protected void addEffects() {
    }

    protected Image getIcon() {
        return getImage();
    }

    protected Image getImage() {
        return SWTResources.getImageRes("/icons/full/obj16/read_obj.gif");
    }

    public Object get() {
        return result;
    }

    public void set(Object result) {
        this.result = result;
    }

    protected void execute() throws CheckException {
    }

    protected void createContents() throws SWTException, CreateException {
        shell = new Shell(getParent(), SWT.APPLICATION_MODAL | SWT.TITLE
                | SWT.RESIZE | SWT.CLOSE);
        shell.setImage(getIcon());
        shell.setLayout(new FormLayout());

        String title = getTitle();
        if (title != null)
            shell.setText(title);

        final Label iconLabel = new Label(shell, SWT.NONE);
        iconLabel.setImage(getImage());
        final FormData fd_iconLabel = new FormData();
        fd_iconLabel.left = new FormAttachment(0, margin);
        fd_iconLabel.top = new FormAttachment(0, margin);
        iconLabel.setLayoutData(fd_iconLabel);

        final Composite detail = new Composite(shell, SWT.NONE);
        detail.setLayout(new FillLayout());
        final FormData fd_detail = new FormData();
        fd_detail.left = new FormAttachment(iconLabel, padding, SWT.RIGHT);
        fd_detail.top = new FormAttachment(iconLabel, 0, SWT.TOP);
        fd_detail.right = new FormAttachment(100, -margin);
        detail.setLayoutData(fd_detail);
        createDetail(detail);

        Composite ops = new Composite(shell, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        ops.setLayout(gridLayout);
        final FormData fd_ops = new FormData();
        fd_ops.left = new FormAttachment(iconLabel, 0, SWT.LEFT);
        fd_ops.right = new FormAttachment(detail, 0, SWT.RIGHT);
        fd_ops.bottom = new FormAttachment(100, -margin);
        ops.setLayoutData(fd_ops);
        createOps(ops);

        body = new Composite(shell, SWT.NONE);
        body.setLayout(new FillLayout());
        final FormData fd_body = new FormData();
        fd_body.left = new FormAttachment(iconLabel, 0, SWT.LEFT);
        fd_body.top = new FormAttachment(detail, padding, SWT.BOTTOM);
        fd_body.right = new FormAttachment(detail, 0, SWT.RIGHT);
        fd_body.bottom = new FormAttachment(ops, -padding, SWT.TOP);
        body.setLayoutData(fd_body);
        createBody(body);
    }

    protected void createDetail(Composite parent) throws SWTException,
            CreateException {
        String title = getTitle();
        if (title != null) {
            Label titleLabel = new Label(parent, SWT.NONE);
            titleLabel.setText(title);
        }
    }

    protected void createBody(Composite parent) throws SWTException,
            CreateException {
        new FixSizeComposite(parent, SWT.NONE, 1, 1);
        // Label label = new Label(parent, SWT.BORDER);
        // label.setText("BODY");
    }

    protected void createOps(Composite parent) throws SWTException,
            CreateException {
        final Composite buttons = new Composite(parent, SWT.NONE);
        final GridData gd_buttons = new GridData(SWT.RIGHT, SWT.CENTER, true,
                false);
        buttons.setLayoutData(gd_buttons);
        final RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginRight = 0;
        rowLayout.marginLeft = 0;
        rowLayout.pack = false;
        buttons.setLayout(rowLayout);
        createButtons(buttons);
    }

    protected void createButtons(Composite parent) throws SWTException,
            CreateException {
        addOKButton(parent);
        addCancelButton(parent);
    }

    protected static SWTInteraction interact;
    static {
        interact = new SWTInteraction(SWT.APPLICATION_MODAL);
    }

    protected void accept() {
        try {
            execute();
        } catch (CheckException ex) {
            interact.alert("Check Failure", ex);
            return;
        }
        shell.dispose();
        canceled = false;
    }

    protected Button addOKButton(Composite parent) throws SWTException {
        final Button okButton = new Button(parent, SWT.NONE);
        okButton.setSelection(true);
        final RowData rd_okButton = new RowData();
        rd_okButton.height = buttonHeight;
        okButton.setLayoutData(rd_okButton);
        okButton.setImage(SWTResourceManager.getImage(SimpleDialog.class,
                "/icons/full/obj16/lrun_obj.gif"));
        okButton.setText("&OK");
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
        final RowData rd_okButton = new RowData();
        rd_okButton.height = buttonHeight;
        cancelButton.setImage(SWTResourceManager.getImage(SimpleDialog.class,
                "/icons/full/obj16/delete_obj.gif"));
        cancelButton.setText("&Cancel");
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
        final RowData rd_yesButton = new RowData();
        rd_yesButton.height = buttonHeight;
        yesButton.setLayoutData(rd_yesButton);
        yesButton.setImage(SWTResourceManager.getImage(SimpleDialog.class,
                "/icons/full/obj16/lrun_obj.gif"));
        yesButton.setText("&Yes");
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
        final RowData rd_okButton = new RowData();
        rd_okButton.height = buttonHeight;
        noButton.setImage(SWTResourceManager.getImage(SimpleDialog.class,
                "/icons/full/obj16/delete_obj.gif"));
        noButton.setText("&No");
        noButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                set(false);
                accept();
            }
        });
        return noButton;
    }

}
