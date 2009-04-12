package net.bodz.dist.ins;

import net.bodz.bas.types.util.Strings;
import net.bodz.dist.ins.builtins.GUISession;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.controls.DetailComposite;
import net.bodz.swt.controls.DetailSwitchEvent;
import net.bodz.swt.controls.DetailSwitchListener;
import net.bodz.swt.gui.pfl.PageComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @test ProgressPageTest
 */
public class ProgressPage extends PageComposite {

    private GUISession      session;
    private boolean         done;

    private Label           imageLabel;
    private Label           actionLabel;

    private int             progressSize = 1000;
    private ProgressBar     progressBar;
    private Label           progressInfo;

    private int             logMax       = 10000;
    private DetailComposite logDetail;
    private List            logList;

    private Composite       counterbar;
    private Label           elapsedLabel;
    private Label           remainingLabel;

    public ProgressPage(GUISession session, Composite parent, int style) {
        super(parent, style);
        this.session = session;

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        imageLabel = new Label(this, SWT.NONE);

        actionLabel = new Label(this, SWT.WRAP);
        actionLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
                false));

        progressBar = new ProgressBar(this, SWT.NONE);
        progressBar.setMaximum(progressSize);
        progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
                false, 2, 1));

        progressInfo = new Label(this, SWT.NONE);
        progressInfo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
                false, 2, 1));
        progressInfo.setText("0%"); //$NON-NLS-1$

        logDetail = new DetailComposite(this, SWT.NONE) {
            @Override
            protected void createContents(Composite parent, int style) {
                logList = new List(parent, style);

                final ToolItem copyItem = addToolItem(SWT.PUSH);
                copyItem.setImage(null);
                copyItem.setToolTipText(PackNLS.getString("ProgressPage.copyClipboard")); //$NON-NLS-1$
                copyItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        copyLogs();
                    }
                });
                final ToolItem pauseItem = addToolItem(SWT.CHECK);
                pauseItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        boolean pausing = pauseItem.getSelection();
                        setPausing(pausing);
                    }
                });
                final ToolItem cancelItem = addToolItem(SWT.CHECK);
                cancelItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        boolean canceling = cancelItem.getSelection();
                        setCanceling(canceling);
                    }
                });
            }
        };
        GridData gd_detail = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        logDetail.setLayoutData(gd_detail);
        logDetail.setText(PackNLS.getString("ProgressPage.logsLabel")); //$NON-NLS-1$
        logDetail.addDetailSwitchListener(new DetailSwitchListener() {
            @Override
            public void detailSwitch(DetailSwitchEvent e) {
                layout();
            }
        });

        final Composite statusbar = new Composite(this, SWT.NONE);
        final GridLayout gridLayout_1 = new GridLayout();
        gridLayout_1.numColumns = 2;
        gridLayout_1.verticalSpacing = 0;
        gridLayout_1.marginWidth = 0;
        gridLayout_1.marginHeight = 0;
        gridLayout_1.horizontalSpacing = 0;
        statusbar.setLayout(gridLayout_1);
        final GridData gd_statusbar = new GridData(SWT.FILL, SWT.CENTER, false,
                false, 2, 1);
        statusbar.setLayoutData(gd_statusbar);

        counterbar = new Composite(statusbar, SWT.NONE);
        counterbar.setLayout(new RowLayout());

        final Composite timebar = new Composite(statusbar, SWT.NONE);
        timebar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
        final GridLayout gridLayout_2 = new GridLayout();
        gridLayout_2.numColumns = 5;
        timebar.setLayout(gridLayout_2);

        final Label _elapsedLabel = new Label(timebar, SWT.NONE);
        _elapsedLabel.setText(PackNLS.getString("ProgressPage.elapsedTime")); //$NON-NLS-1$

        elapsedLabel = new Label(timebar, SWT.NONE);
        elapsedLabel.setText("-:-:-"); //$NON-NLS-1$

        final Label sep = new Label(timebar, SWT.SEPARATOR);
        final GridData gd_sep = new GridData(SWT.LEFT, SWT.FILL, false, false);
        gd_sep.heightHint = 1;
        sep.setLayoutData(gd_sep);

        final Label _remainingLabel = new Label(timebar, SWT.NONE);
        _remainingLabel.setText(PackNLS.getString("ProgressPage.remainingTime")); //$NON-NLS-1$

        remainingLabel = new Label(timebar, SWT.NONE);
        remainingLabel.setText("-:-:-"); //$NON-NLS-1$
    }

    @Override
    public boolean isLocked() {
        return done;
    }

    public void setImage(Image image) {
        imageLabel.setImage(image);
    }

    public void setText(String s) {
        actionLabel.setText(s);
    }

    public void setTextLog(int level, String s) {
        setText(s);
        log(level, s);
    }

    public void log(int level, String s) {
        // enhanced list:
        // Image icon = getLevelIcon(level);
        // logsList.add(icon, s);
        logList.add(s);
        int count = logList.getItemCount();
        if (count > logMax)
            logList.remove(0, count - logMax);
        // logsList.scrollTo(end);
    }

    public void setProgress(float k) {
        int index = (int) (k * progressSize);
        progressBar.setSelection(index);
    }

    public void beginTiming() {
    }

    void copyLogs() {
        Display display = getDisplay();
        Clipboard clipboard = new Clipboard(display);

        String s = null;
        String[] logs = logList.getItems();
        s = Strings.join("\n", logs); //$NON-NLS-1$

        if (s == null || s.isEmpty())
            clipboard.clearContents();
        else {
            Object[] data = { s };
            Transfer[] dataTypes = { TextTransfer.getInstance() };
            clipboard.setContents(data, dataTypes);
        }
    }

    void setPausing(boolean pausing) {
    }

    void setCanceling(boolean canceling) {
        session.setCanceling(canceling);
    }

}
