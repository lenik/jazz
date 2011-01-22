package net.bodz.swt.gui.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.ui.UserInterface;
import net.bodz.swt.dialogs.SimpleDialog;
import net.bodz.swt.gui.DialogUI;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.util.EmptyComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

/**
 * @test
 */
public class ThreadsMonitor extends SimpleDialog {

    private Map<Long, Thread> threadMap;
    private boolean           showDaemon;
    private int               interval;

    private List              threadList;

    private UserInterface     UI;

    public ThreadsMonitor(Shell parent, int style) {
        super(parent, style, GUINLS.getString("ThreadsMonitor.threadsMonitor")); //$NON-NLS-1$
        threadMap = new HashMap<Long, Thread>();
        UI = new DialogUI(parent);
    }

    @Override
    public Object open() {
        return open(false);
    }

    @Override
    protected void createTopBar(Composite parent) {
        new EmptyComposite(parent, SWT.NONE);
    }

    @Override
    protected void createBody(Composite parent) throws CreateException {
        final GridLayout layout = new GridLayout(4, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        parent.setLayout(layout);

        final Button showDaemonCheck = new Button(parent, SWT.CHECK);
        showDaemonCheck.setText(GUINLS.getString("ThreadsMonitor.showDaemons")); //$NON-NLS-1$
        showDaemonCheck.setLayoutData(new GridData(//
                GridData.BEGINNING, GridData.CENTER, true, false));
        showDaemonCheck.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                showDaemon = showDaemonCheck.getSelection();
                refresh();
            }
        });

        final Label intervalImage = new Label(parent, SWT.NONE);
        intervalImage.setImage(SWTResources.getImageRes("/icons/full/obj16/refresh_tab.gif")); //$NON-NLS-1$

        final Label intervalLabel = new Label(parent, SWT.NONE);
        intervalLabel.setText(GUINLS.getString("ThreadsMonitor.refreshInterval")); //$NON-NLS-1$

        final Spinner intervalSpinner = new Spinner(parent, SWT.NONE);
        intervalSpinner.setMinimum(0);
        intervalSpinner.setMaximum(60);
        intervalSpinner.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                interval = intervalSpinner.getSelection();
                refresh();
            }
        });

        threadList = new List(parent, SWT.V_SCROLL | SWT.BORDER);
        GridData listData = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 1);
        listData.heightHint = 240;
        threadList.setLayoutData(listData);
        refresh();
    }

    @Override
    protected void createButtons(Composite parent) throws CreateException {
        Button button = addOKButton(parent);
        button.setText(GUINLS.getString("ThreadsMonitor.close")); //$NON-NLS-1$
        button.setToolTipText(GUINLS.getString("ThreadsMonitor.closeDialog")); //$NON-NLS-1$
        button.setImage(SWTResources.getImageRes("/icons/elcl16/nav_forward.gif")); //$NON-NLS-1$
    }

    @Override
    protected void createUserButtons(Composite parent) throws CreateException {
        Button killAllButton = new Button(parent, SWT.NONE);
        killAllButton.setText(GUINLS.getString("ThreadsMonitor.killAll")); //$NON-NLS-1$
        killAllButton.setImage(SWTResources.getImageRes("/icons/full/elcl16/terminate_all_co.gif")); //$NON-NLS-1$
        killAllButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                killAll();
            }
        });

        Button killButton = new Button(parent, SWT.NONE);
        killButton.setText(GUINLS.getString("ThreadsMonitor.kill")); //$NON-NLS-1$
        killButton.setImage(SWTResources.getImageRes("/icons/full/elcl16/terminate_co.gif")); //$NON-NLS-1$
        killButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                kill();
            }
        });

        Button traceButton = new Button(parent, SWT.NONE);
        traceButton.setText(GUINLS.getString("ThreadsMonitor.stackTrace")); //$NON-NLS-1$
        traceButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/stckframe_running_obj.gif")); //$NON-NLS-1$
        traceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                trace();
            }
        });

        Button mailButton = new Button(parent, SWT.NONE);
        mailButton.setText(GUINLS.getString("ThreadsMonitor.sendMail")); //$NON-NLS-1$
        mailButton.setImage(SWTResources.getImageRes("/icons/full/obj16/text_edit.gif")); //$NON-NLS-1$
        mailButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                sendMail();
            }
        });
    }

    void refresh() {
        int activeThreads = Thread.activeCount();
        Thread[] threads = new Thread[activeThreads];
        Thread.enumerate(threads);
        threadList.removeAll();
        threadMap.clear();
        for (Thread t : threads) {
            if (!showDaemon && t.isDaemon())
                continue;
            long id = t.getId();
            threadMap.put(id, t);
            String description = id + ": " + t.toString(); //$NON-NLS-1$
            threadList.add(description);
        }
    }

    void killAll() {
        System.exit(1);
    }

    Thread getSelection() {
        int i = threadList.getSelectionIndex();
        if (i == -1)
            return null;
        String s = threadList.getItem(i);
        int colon = s.indexOf(':');
        if (colon == -1)
            throw new UnexpectedException();
        String _id = s.substring(0, colon);
        long id = Long.parseLong(_id);
        Thread thread = threadMap.get(id);
        return thread;
    }

    @SuppressWarnings("deprecation")
    void kill() {
        Thread thread = getSelection();
        if (thread == null)
            return;
        if (UI.confirm(GUINLS.getString("ThreadsMonitor.confirmKill") + thread + "?")) { //$NON-NLS-1$ //$NON-NLS-2$
            thread.interrupt();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (thread.isAlive()) {
                System.err.println(GUINLS.getString("ThreadsMonitor.forceToStop") + thread); //$NON-NLS-1$
                thread.stop();
            }
            refresh();
        }
    }

    void trace() {
        Thread thread = getSelection();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        // StringBuffer buf = new StringBuffer(stackTrace.length * 50);
        UI.alert(GUINLS.getString("ThreadsMonitor.stackTraceOf") + thread, stackTrace); //$NON-NLS-1$
    }

    void sendMail() {

    }

}
