package net.bodz.swt.reflect.util;

import net.bodz.swt.reflect.util.ThreadsMonitor;

import org.eclipse.swt.SWT;

public class ThreadsMonitorTest {

    public static void main(String[] args) {
        ThreadsMonitor monitor = new ThreadsMonitor(null, SWT.NONE);
        monitor.open();
    }

}
