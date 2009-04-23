package net.bodz.dist.ins;

import net.bodz.bas.io.term.BufferedTerminal;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.DurationChangeEvent;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util.ProgressChangeEvent;
import net.bodz.bas.util.StatusChangeEvent;
import net.bodz.dist.nls.PackNLS;
import net.bodz.swt.controls.DetailSwitchEvent;
import net.bodz.swt.controls.DetailSwitchListener;
import net.bodz.swt.controls.WindowComposite;
import net.bodz.swt.gui.DialogUI;
import net.bodz.swt.gui.pfl.PageComposite;
import net.bodz.swt.gui.pfl.PageFlow;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
class ProgressPage extends PageComposite {

    private static final int    BLOCK        = 0;
    private static final int    DONE         = 1;
    private static final int    CANCELED     = 2;

    private final ISession      session;
    private final UserInterface UI;

    private int                 state        = BLOCK;
    private Thread              installThread;

    private Label               imageLabel;
    private Label               statusLabel;

    private int                 progressSize = 1000;
    private ProgressBar         progressBar;
    private Label               progressText;

    private int                 logMax       = 10000;
    private WindowComposite     logDetail;
    private List                logList;

    private Composite           counterbar;
    private Label               elapsedLabel;
    private Label               remainingLabel;

    public ProgressPage(ISession session, Composite parent, int style) {
        super(parent, style);

        this.session = session;
        this.UI = new DialogUI(getShell());

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        setLayout(gridLayout);

        imageLabel = new Label(this, SWT.NONE);
        imageLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

        statusLabel = new Label(this, SWT.NONE); // SWT.WRAP);
        statusLabel.setText("STATUS");
        GridData statusData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        // statusData.heightHint = 50;
        statusLabel.setLayoutData(statusData);

        progressBar = new ProgressBar(this, SWT.NONE);
        progressBar.setMaximum(progressSize);
        progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        progressText = new Label(this, SWT.NONE);
        progressText.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
        progressText.setText("0%"); //$NON-NLS-1$

        logDetail = new WindowComposite(this, SWT.NONE, true, this) {
            @Override
            protected void createContents(Composite parent, int style) {
                logList = new List(parent, SWT.H_SCROLL | SWT.V_SCROLL);

                final ToolItem copyItem = addToolItem(SWT.PUSH);
                copyItem.setImage(SWTResources.getImageRes("/icons/full/etool16/copy_edit.gif"));
                copyItem.setToolTipText(PackNLS.getString("ProgressPage.copyClipboard")); //$NON-NLS-1$
                copyItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        copyLogs();
                    }
                });
                final ToolItem pauseItem = addToolItem(SWT.CHECK);
                pauseItem
                        .setImage(SWTResources.getImageRes("/icons/full/etool16/term_restart.gif"));
                pauseItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        boolean pausing = pauseItem.getSelection();
                        setPausing(pausing);
                    }
                });
                final ToolItem cancelItem = addToolItem(SWT.CHECK);
                cancelItem
                        .setImage(SWTResources.getImageRes("/icons/full/elcl16/terminate_co.gif"));
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
        final GridData gd_statusbar = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
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
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return "Installing Software";
    }

    @Override
    public boolean isLocked() {
        return state == BLOCK;
    }

    protected void setState(int state) {
        this.state = state;
        switch (state) {
        case DONE:
            setExitState("next");
            break;
        case CANCELED:
            setExitState("cancel");
            break;
        }
        firePageStateChange();
    }

    /**
     * Execute the install immediately when entered into this page
     */
    @Override
    public void enter(String prev, int reason) {
        if (reason != PageFlow.FORWARD)
            return;
        logList.removeAll();
        final PPExecutor executor = new PPExecutor();
        setState(BLOCK);
        installThread = new Thread() {
            @Override
            public void run() {
                int state = CANCELED;
                try {
                    executor.install();
                    state = DONE;
                } catch (SessionException e) {
                    UserInterface UI = session.getUserInterface();
                    UI.alert("Install Error", e);
                } finally {
                    final int newState = state;
                    getDisplay().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            setState(newState);
                        }
                    });
                }
            }
        };
        installThread.start();
    }

    class PPExecutor extends ProjectExecutor {

        public PPExecutor() {
            super(ProgressPage.this.session, ProgressPage.this.UI, new PLogTerm());
        }

        @Override
        public void progressChange(final ProgressChangeEvent e) {
            final double progress = e.getProgress();
            final Object source = e.getSource();
            System.err.printf("%.3f: %s\n", progress, source);
            getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setProgress(progress);
                }
            });
        }

        @Override
        public void durationChange(DurationChangeEvent e) {
            getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    // setDu...
                }
            });
        }

        @Override
        public void statusChange(StatusChangeEvent e) {
            Object status = e.getStatus();
            final String text = String.valueOf(status);
            getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(text);
                }
            });
        }

        @Override
        public void exceptionThrown(Exception ex) {
            UI.alert("Failed to install: " + ex.getMessage(), ex);
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            Exception ex = e.getException();
            int answer = UI.ask("Error happens: " + ex.getMessage() + ", continue?", e,
                    Proposals.ignore, Proposals.cancel, Proposals.debug);
            switch (answer) {
            case 0:
                e.setRecovered(true);
                break;
            case 1:
                return;
            case 2:
                throw new RuntimeException(ex);
            }
        }

    }

    public void setImage(Image image) {
        imageLabel.setImage(image);
        layout();
    }

    public void setText(String s) {
        statusLabel.setText(s);
        // layout();
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
        if (count > logMax) {
            logList.remove(0, count - logMax);
            count -= logMax;
        }
        logList.select(count - 1);
        logList.showSelection();
    }

    public void setProgress(double k) {
        int index = (int) (k * progressSize);
        progressBar.setSelection(index);
    }

    public void beginTiming() {
    }

    private void copyLogs() {
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
        // XXX - session.setCanceling(canceling);
    }

    class PTerminal extends BufferedTerminal {

        final int level;

        public PTerminal(int level) {
            this.level = level;
        }

        @Override
        protected void _p(final String s) {
            getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                    log(level, s);
                }
            });
        }

        @Override
        protected void _t(final String s) {
            getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                }
            });
        }

    }

    class PLogTerm extends LogTerm {

        public PLogTerm() {
            int level = session.getLogger().getLevel();
            setLevel(level);
        }

        @Override
        public Terminal filter(int level) {
            return new PTerminal(level);
        }

    }

}
