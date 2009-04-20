package net.bodz.swt.gui.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.ui.Interaction;
import net.bodz.swt.controls.helper.EmptyComposite;
import net.bodz.swt.dialogs.SimpleDialog;
import net.bodz.swt.gui.DialogInteraction;
import net.bodz.swt.util.SWTResources;

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

    private Interaction       iact;

    public ThreadsMonitor(Shell parent, int style) {
        super(parent, style, "Threads Monitor");
        threadMap = new HashMap<Long, Thread>();
        iact = new DialogInteraction(parent);
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
        showDaemonCheck.setText("Show &daemon threads");
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
        intervalImage.setImage(SWTResources
                .getImageRes("/icons/full/obj16/refresh_tab.gif"));

        final Label intervalLabel = new Label(parent, SWT.NONE);
        intervalLabel.setText("Refresh &Interval");

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
        GridData listData = new GridData(GridData.FILL, GridData.FILL, true,
                true, 4, 1);
        listData.heightHint = 240;
        threadList.setLayoutData(listData);
        refresh();
    }

    @Override
    protected void createButtons(Composite parent) throws CreateException {
        Button button = addOKButton(parent);
        button.setText("Close");
        button
                .setToolTipText("Close this dialog only, all threads are continue to run");
        button.setImage(SWTResources
                .getImageRes("/icons/elcl16/nav_forward.gif"));
    }

    @Override
    protected void createUserButtons(Composite parent) throws CreateException {
        Button killAllButton = new Button(parent, SWT.NONE);
        killAllButton.setText("Kill All");
        killAllButton.setImage(SWTResources
                .getImageRes("/icons/full/elcl16/terminate_all_co.gif"));
        killAllButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                killAll();
            }
        });

        Button killButton = new Button(parent, SWT.NONE);
        killButton.setText("Kill");
        killButton.setImage(SWTResources
                .getImageRes("/icons/full/elcl16/terminate_co.gif"));
        killButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                kill();
            }
        });

        Button traceButton = new Button(parent, SWT.NONE);
        traceButton.setText("Stack");
        traceButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/stckframe_running_obj.gif"));
        traceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                trace();
            }
        });

        Button mailButton = new Button(parent, SWT.NONE);
        mailButton.setText("Mail");
        mailButton.setImage(SWTResources
                .getImageRes("/icons/full/obj16/text_edit.gif")); //$NON-NLS-1$
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
            String description = id + ": " + t.toString();
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
        if (iact.confirm("Are you really want to kill " + thread + "?")) {
            thread.interrupt();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (thread.isAlive()) {
                System.err.println("Force to stop " + thread);
                thread.stop();
            }
            refresh();
        }
    }

    void trace() {
        Thread thread = getSelection();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        // StringBuffer buf = new StringBuffer(stackTrace.length * 50);
        iact.alert("Stack trace of " + thread, stackTrace);
    }

    void sendMail() {

    }

}
