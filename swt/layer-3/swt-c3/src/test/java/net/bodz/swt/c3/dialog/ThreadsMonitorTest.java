package net.bodz.swt.c3.dialog;

import org.eclipse.swt.SWT;

public class ThreadsMonitorTest {

    public static void main(String[] args) {
        ThreadsMonitor monitor = new ThreadsMonitor(null, SWT.NONE);
        monitor.open();
    }

}
