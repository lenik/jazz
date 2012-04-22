package net.bodz.art.installer;

import java.util.ArrayList;

import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.api.AbstractLogger;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.DurationChangeEvent;
import net.bodz.bas.util.IJob;
import net.bodz.bas.util.JobObserver;
import net.bodz.bas.util.ProgressChangeEvent;
import net.bodz.bas.util.StatusChangeEvent;
import net.bodz.bas.util.exception.RecoverableExceptionEvent;
import net.bodz.swt.gui.pfl.AbstractPage;
import net.bodz.swt.gui.pfl.IPage;
import net.bodz.swt.gui.pfl.PageException;
import net.bodz.swt.gui.pfl.PageMethod;
import net.bodz.swt.gui.pfl.ServiceContext;
import net.bodz.swt.reflect.util.DialogUI;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.DetailSwitchEvent;
import net.bodz.swt.widgets.DetailSwitchListener;
import net.bodz.swt.widgets.WindowComposite;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

class ProgressPage
        extends AbstractPage {

    private static final int BLOCK = 0;
    private static final int DONE = 1;
    private static final int CANCELED = 2;

    private final ISession session;

    private int state = BLOCK;
    private SessionJob rootJob;
    private Thread jobThread;

    private Label imageLabel;
    private Label statusLabel;

    private int progressSize = 1000;
    private ProgressBar progressBar;
    private Label progressText;

    private int logMax = 10000;
    private WindowComposite logDetail;
    private ToolItem pauseItem;
    private ToolItem cancelItem;
    private List logList;
    private ArrayList<Object> logListData;

    private Composite counterbar;
    private Label elapsedLabel;
    private Label remainingLabel;

    public ProgressPage(final ISession session) {
        this.session = session;

        addMethod(new PageMethod(DonePage.class));
        addMethod(new PageMethod(CanceledPage.class));
    }

    @Override
    public ImageData getPageIcon() {
        return super.getPageIcon();
    }

    @Override
    public String getPageTitle() {
        return PackNLS.getString("ProgressPage.installing"); //$NON-NLS-1$
    }

    @Override
    protected void createContents(final Composite holder) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        holder.setLayout(gridLayout);

        imageLabel = new Label(holder, SWT.NONE);
        imageLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

        statusLabel = new Label(holder, SWT.NONE); // SWT.WRAP);
        statusLabel.setText("STATUS"); //$NON-NLS-1$
        GridData statusData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        // statusData.heightHint = 50;
        statusLabel.setLayoutData(statusData);

        progressBar = new ProgressBar(holder, SWT.NONE);
        progressBar.setMaximum(progressSize);
        progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        progressText = new Label(holder, SWT.NONE);
        progressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        progressText.setAlignment(SWT.CENTER);
        progressText.setText("0%"); //$NON-NLS-1$

        logDetail = new WindowComposite(holder, SWT.NONE, true, holder) {

            @Override
            protected void createContents(Composite parent, int style) {
                logList = new List(parent, SWT.H_SCROLL | SWT.V_SCROLL);

                logList.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetDefaultSelected(SelectionEvent e) {
                        int index = logList.getSelectionIndex();
                        if (index != -1) {
                            Object data = logList.getData(String.valueOf(index));
                            if (data != null) {
                                UserInterface UI = session.getUserInterface();
                                UI.alert("Log Detail: " + data, data);
                            }
                        }
                    }
                });

                final ToolItem copyItem = addToolItem(SWT.PUSH);
                copyItem.setImage(SWTResources.getImageRes("/icons/full/etool16/copy_edit.gif")); //$NON-NLS-1$
                copyItem.setToolTipText(PackNLS.getString("ProgressPage.copyClipboard")); //$NON-NLS-1$
                copyItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        copyLogs();
                    }
                });
                pauseItem = addToolItem(SWT.CHECK);
                pauseItem.setImage(SWTResources.getImageRes("/icons/full/etool16/term_restart.gif")); //$NON-NLS-1$
                pauseItem.addSelectionListener(new SelectionAdapter() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        boolean pausing = pauseItem.getSelection();
                        Thread thread = jobThread; // sync to below
                        if (thread != null) {
                            if (pausing)
                                thread.suspend();
                            else
                                thread.resume();
                        } else {
                            pauseItem.setSelection(false);
                        }
                    }
                });
                cancelItem = addToolItem(SWT.CHECK);
                cancelItem.setImage(SWTResources.getImageRes("/icons/full/elcl16/terminate_co.gif")); //$NON-NLS-1$
                cancelItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        boolean canceling = cancelItem.getSelection();
                        SessionJob job = rootJob; // for synchronization.
                        if (job != null) {
                            if (canceling)
                                job.stop();
                            else
                                job.run();
                        } else
                            cancelItem.setSelection(false);
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
                holder.layout();
            }
        });

        final Composite statusbar = new Composite(holder, SWT.NONE);
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
    public boolean isSticked() {
        return state == BLOCK;
    }

    protected void setState(int state) {
        java.util.List<PageMethod> old = getMethods();
        this.state = state;
        switch (state) {
        case BLOCK:
            // reflected by isSticked().
            break;
        case DONE:
            setMethods(new PageMethod(DonePage.class, "quit"));
            break;
        case CANCELED:
            setMethods(new PageMethod(CanceledPage.class, "canceled"));
            break;
        }
        propertyChangeSupport.firePropertyChange(IPage.PROP_METHODS, old, getMethods());
    }

    /**
     * Execute the install immediately when entered into this page
     */
    @Override
    public TreePath service(ServiceContext context)
            throws PageException {
        logList.removeAll();
        setState(BLOCK);

        final LogTerm logger0 = session.getLogger();
        session.setLogger(new PLogTerm());
        rootJob = session.getProject().execute(IComponent.INSTALL, session);
        jobThread = new Thread() {
            @Override
            public void run() {
                int state = CANCELED;
                DialogUI _UI = new DialogUI(new Shell(new Display()));
                try {
                    session.loadRegistry();
                    if (rootJob != null) {
                        if (logger0.showDebug())
                            rootJob.dump(Stdio.cout);
                        Observer observer = new Observer(_UI);
                        rootJob.setUserInterface(_UI);
                        observer.bind(rootJob);
                        rootJob.run();
                    }
                    int jobState = rootJob.getState();
                    if (jobState == IJob.TERMINATED)
                        state = DONE;
                } catch (Exception e) {
                    _UI.alert(PackNLS.getString("ProgressPage.installError"), e); //$NON-NLS-1$
                } finally {
                    rootJob = null;
                    jobThread = null;
                    session.closeAttachments();
                    session.setLogger(logger0);
                    final int newState = state;
                    pageContainer.getDisplay().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            setState(newState);
                            cancelItem.setSelection(false);
                        }
                    });
                }
            }
        };
        jobThread.start();
        return null;
    }

    class Observer
            extends JobObserver {

        final UserInterface UI;

        public Observer(UserInterface UI) {
            if (UI == null)
                throw new NullPointerException("UI"); //$NON-NLS-1$
            this.UI = UI;
        }

        @Override
        public void progressChange(final ProgressChangeEvent e) {
            final double progress = e.getProgress();
            final Object source = e.getSource();
            System.err.printf("%.3f: %s\n", progress, source); //$NON-NLS-1$
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setProgress(progress);
                }
            });
        }

        @Override
        public void durationChange(DurationChangeEvent e) {
            pageContainer.getDisplay().asyncExec(new Runnable() {
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
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(text);
                }
            });
        }

        @Override
        public void exceptionThrown(Exception ex) {
            UI.alert(PackNLS.getString("ProgressPage.failedToInstall") + ex.getMessage(), ex); //$NON-NLS-1$
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            Exception ex = e.getException();
            int answer = UI
                    .ask(PackNLS.getString("ProgressPage.errorHappens") + ex.getMessage() + PackNLS.getString("ProgressPage._continue"), e, //$NON-NLS-1$ //$NON-NLS-2$
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
        pageHolder.layout();
    }

    public void setText(String s) {
        statusLabel.setText(s);
        // pageHolder.layout();
    }

    public void setTextAndLog(int level, String s, Object data) {
        setText(s);
        log(level, s, data);
    }

    public void log(int level, String s, Object data) {
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

    public void setProgress(double progress) {
        int index = (int) (progress * progressSize);
        progressBar.setSelection(index);
        String s = String.format("%.0f%%", progress * 100); //$NON-NLS-1$
        progressText.setText(s);
    }

    public void beginTiming() {
    }

    private void copyLogs() {
        Display display = pageContainer.getDisplay();
        Clipboard clipboard = new Clipboard(display);

        String s = null;
        String[] logs = logList.getItems();
        s = StringArray.join("\n", logs); //$NON-NLS-1$

        if (s == null || s.isEmpty())
            clipboard.clearContents();
        else {
            Object[] data = { s };
            Transfer[] dataTypes = { TextTransfer.getInstance() };
            clipboard.setContents(data, dataTypes);
        }
    }

    class PTerminal
            extends AbstractLogSink { // BufferedTerminal

        final int level;

        public PTerminal(int level) {
            this.level = level;
        }

        @Override
        protected void _p(final String s) {
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                    log(level, s, null);
                }
            });
        }

        @Override
        protected void _t(final String s) {
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                }
            });
        }

    }

    class PLogTerm
            extends AbstractLogger {

        public PLogTerm() {
            int level = session.getLogger().getLevel();
            setLevel(level);
        }

        @Override
        public ILogSink getLogSink(int level) {
            return new PTerminal(level);
        }

    }

}
