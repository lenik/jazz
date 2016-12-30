package net.bodz.pkg.sisi.page;

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
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.t.pojo.PathEntries;
import net.bodz.bas.ui.dialog.DirectiveCommands;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.bas.ui.model.action.AbstractProgressMonitor;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.swt.c.composite.DetailSwitchEvent;
import net.bodz.swt.c.composite.DetailSwitchListener;
import net.bodz.swt.c.composite.WindowComposite;
import net.bodz.swt.c.dialog.SwtUserDialogs;
import net.bodz.swt.c.pageflow.IPage;
import net.bodz.swt.c.pageflow.PageException;
import net.bodz.swt.c.pageflow.PageMethod;
import net.bodz.swt.c.pageflow.ServiceContext;
import net.bodz.swt.c.resources.SWTResources;

class ProgressPage
        extends SisiPage {

    private static final int BLOCK = 0;
    private static final int DONE = 1;
    private static final int CANCELED = 2;

    private final ISisProject project;

    Monitor monitor;
    IUserDialogs dialogs;

    private int state = BLOCK;
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
    private ListBox logList;
    private ArrayList<Object> logListData;

    private Composite counterbar;
    private Label elapsedLabel;
    private Label remainingLabel;

    public ProgressPage(final ISisProject project) {
        this.project = project;

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
                logList = new ListBox(parent, SWT.H_SCROLL | SWT.V_SCROLL);

                logList.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetDefaultSelected(SelectionEvent e) {
                        int index = logList.getSelectionIndex();
                        if (index != -1) {
                            Object data = logList.getData(String.valueOf(index));
                            if (data != null) {
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
                        boolean selection = cancelItem.getSelection();
                        if (selection)
                            monitor.cancel();
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

        final IUserDialogs dialogs = new SwtUserDialogs(new Shell(new Display()));
        final Monitor monitor = new Monitor(dialogs);
        final IOptions options = new Options().addOption(IUserDialogs.class, dialogs);

        jobThread = new Thread() {
            @Override
            public void run() {
                project.install(monitor, options);
            }
        };
        jobThread.start();
        return null;
    }

    class Monitor
            extends AbstractProgressMonitor {

        private final IUserDialogs userDialogs;

        int totalProgress;
        int progress;

        public Monitor(IUserDialogs userDialogs) {
            if (userDialogs == null)
                throw new NullPointerException("userDialogs");
            this.userDialogs = userDialogs;
        }

        @Override
        public void enter(String taskName, int parentDiv, int totalProgress, long estimatedDuration) {
            this.totalProgress = totalProgress;
        }

        @Override
        public void leave() {
        }

        @Override
        protected void updateProgress() {
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    ProgressPage.this.setProgress((double) progress / totalProgress);
                }
            });
        }

        @Override
        public boolean _fatal(int delta, Throwable ex, Object message) {
            userDialogs.alert(tr._("Failed to install: ") + ex.getMessage(), ex);
            return false;
        }

        @Override
        public boolean _error(int delta, Throwable ex, Object message) {
            int answer = userDialogs.ask(tr._("Error happens: ") + ex.getMessage() + tr._(", continue?"), ex, //
                    DirectiveCommands.retry, //
                    DirectiveCommands.ignore, //
                    DirectiveCommands.cancel, //
                    DirectiveCommands.debug);
            switch (answer) {
            case 0: // retry
                return true;
            case 1: // ignore
                return false;
            case 2: // cancel
                cancel();
                return false;
            case 3: // debug
            default:
                // Set breakpoint at following line:
                return true;
            }
        }

        @Override
        public void _warn(int delta, Throwable e, Object message) {
            final String text = String.valueOf(message);
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    // add to log list.
                }
            });
        }

        @Override
        public void _info(int delta, Throwable e, Object message) {
            final String text = String.valueOf(message);
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    // add to log list.
                }
            });
        }

        @Override
        public void _status(int delta, Throwable e, Object message) {
            final String text = String.valueOf(message);
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    setText(text);
                }
            });
        }

        @Override
        public void _debug(int delta, Throwable e, Object message) {
            final String text = String.valueOf(message);
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    // add to log list.
                }
            });
        }

    }

    public void setImage(Image image) {
        imageLabel.setImage(image);
        parent.layout();
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
        Display display = parent.getDisplay();
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

}
