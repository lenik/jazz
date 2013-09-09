package net.bodz.swt.c.dialog;

import org.eclipse.swt.SWT;

import net.bodz.swt.c.dialog.ThreadsMonitor;

public class ThreadsMonitorTest {

    public static void main(String[] args) {
        ThreadsMonitor monitor = new ThreadsMonitor(null, SWT.NONE);
        monitor.open();
    }

}
