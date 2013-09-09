package net.bodz.redist.installer;

import java.util.ArrayList;

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
import org.eclipse.swt.widgets.*;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.RecoverableExceptionEvent;
import net.bodz.bas.exec.job.DurationChangeEvent;
import net.bodz.bas.exec.job.IJob;
import net.bodz.bas.exec.job.JobObserver;
import net.bodz.bas.exec.job.ProgressChangeEvent;
import net.bodz.bas.exec.job.StatusChangeEvent;
import net.bodz.bas.gui.dialog.DirectiveCommands;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.AbstractLogger;
import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.Logger;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.swt.c.composite.DetailSwitchEvent;
import net.bodz.swt.c.composite.DetailSwitchListener;
import net.bodz.swt.c.composite.WindowComposite;
import net.bodz.swt.c.dialog.SwtDialogs;
import net.bodz.swt.c.pageflow.AbstractPage;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;
import net.bodz.swt.c.resources.SWTResources;

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
        return tr._("Installing Software");
    }

    @Override
    protected void createContents(final Composite holder) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        holder.setLayout(gridLayout);

        imageLabel = new Label(holder, SWT.NONE);
        imageLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

        statusLabel = new Label(holder, SWT.NONE); // SWT.WRAP);
        statusLabel.setText("STATUS");
        GridData statusData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        // statusData.heightHint = 50;
        statusLabel.setLayoutData(statusData);

        progressBar = new ProgressBar(holder, SWT.NONE);
        progressBar.setMaximum(progressSize);
        progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        progressText = new Label(holder, SWT.NONE);
        progressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        progressText.setAlignment(SWT.CENTER);
        progressText.setText("0%");

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
                                IUserDialogs dialogs = session.getUserDialogs();
                                dialogs.alert("Log Detail: " + data, data);
                            }
                        }
                    }
                });

                final ToolItem copyItem = addToolItem(SWT.PUSH);
                copyItem.setImage(SWTResources.getImageRes("/icons/full/etool16/copy_edit.gif"));
                copyItem.setToolTipText(tr._("Copy"));
                copyItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        copyLogs();
                    }
                });
                pauseItem = addToolItem(SWT.CHECK);
                pauseItem.setImage(SWTResources.getImageRes("/icons/full/etool16/term_restart.gif"));
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
                cancelItem.setImage(SWTResources.getImageRes("/icons/full/elcl16/terminate_co.gif"));
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
        logDetail.setText(tr._("Install Logs: "));
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
        _elapsedLabel.setText(tr._("Elapsed Time: "));

        elapsedLabel = new Label(timebar, SWT.NONE);
        elapsedLabel.setText("-:-:-");

        final Label sep = new Label(timebar, SWT.SEPARATOR);
        final GridData gd_sep = new GridData(SWT.LEFT, SWT.FILL, false, false);
        gd_sep.heightHint = 1;
        sep.setLayoutData(gd_sep);

        final Label _remainingLabel = new Label(timebar, SWT.NONE);
        _remainingLabel.setText(tr._("Remaining Time:"));

        remainingLabel = new Label(timebar, SWT.NONE);
        remainingLabel.setText("-:-:-");
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
    public PathEntries service(ServiceContext context)
            throws PageException {
        logList.removeAll();
        setState(BLOCK);

        final Logger logger0 = session.getLogger();
        session.setLogger(new PageLogger());
        rootJob = session.getProject().execute(IComponent.INSTALL, session);
        jobThread = new Thread() {
            @Override
            public void run() {
                int state = CANCELED;
                SwtDialogs _dialogs = new SwtDialogs(new Shell(new Display()));
                try {
                    session.loadRegistry();
                    if (rootJob != null) {
                        if (logger0.isDebugEnabled())
                            rootJob.dump(Stdio.cout);
                        Observer observer = new Observer(_dialogs);
                        rootJob.setUserDialogs(_dialogs);
                        observer.bind(rootJob);
                        rootJob.run();
                    }
                    int jobState = rootJob.getState();
                    if (jobState == IJob.TERMINATED)
                        state = DONE;
                } catch (Exception e) {
                    _dialogs.alert(tr._("Install Error"), e);
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

        final IUserDialogs userDialogs;

        public Observer(IUserDialogs userDialogs) {
            if (userDialogs == null)
                throw new NullPointerException("userDialogs");
            this.userDialogs = userDialogs;
        }

        @Override
        public void progressChange(final ProgressChangeEvent e) {
            final double progress = e.getProgress();
            final Object source = e.getSource();
            System.err.printf("%.3f: %s\n", progress, source);
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
            userDialogs.alert(tr._("Failed to install: ") + ex.getMessage(), ex);
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            Exception ex = e.getException();
            int answer = userDialogs.ask(
                    tr._("Error happens: ") + ex.getMessage()
                            + tr._(", continue?"), e, DirectiveCommands.ignore, DirectiveCommands.cancel,
                    DirectiveCommands.debug);
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
        _log(level, s, data);
    }

    public void _log(int level, String s, Object data) {
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
        String s = String.format("%.0f%%", progress * 100);
        progressText.setText(s);
    }

    public void beginTiming() {
    }

    private void copyLogs() {
        Display display = pageContainer.getDisplay();
        Clipboard clipboard = new Clipboard(display);

        String s = null;
        String[] logs = logList.getItems();
        s = StringArray.join("\n", logs);

        if (s == null || s.isEmpty())
            clipboard.clearContents();
        else {
            Object[] data = { s };
            Transfer[] dataTypes = { TextTransfer.getInstance() };
            clipboard.setContents(data, dataTypes);
        }
    }

    class PageLogSink
            extends AbstractLogSink { // BufferedTerminal

        final LogLevel level;
        final int priority;

        public PageLogSink(LogLevel level) {
            this.level = level;
            this.priority = level.getPriority();
        }

        @Override
        public void logMessage(Object message) {
            final String s = String.valueOf(message);
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                    _log(priority, s, null);
                }
            });
        }

        @Override
        public void logException(Object message, final Throwable exception) {
            final String s = String.valueOf(message);
            pageContainer.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(s);
                    _log(priority, s, exception);
                }
            });
        }

    }

    class PageLogger
            extends AbstractLogger {

        public PageLogger() {
            Logger sessionLogger = session.getLogger();
            setLevel(sessionLogger.getLevel());
            setDelta(sessionLogger.getDelta());
        }

        @Override
        public ILogSink get(LogLevel level, int delta) {
            if (level.getGroup() == LogLevel.stdGroup) {
            }
            return new PageLogSink(level);
        }

    }

}
