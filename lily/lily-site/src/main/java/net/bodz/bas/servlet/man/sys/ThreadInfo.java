package net.bodz.bas.servlet.man.sys;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadInfo {

    Thread thread;
    StackTraceElement[] stackTrace;

    public ThreadInfo(Thread thread) {
        this.thread = thread;
    }

    public ThreadInfo(Thread thread, StackTraceElement[] stackTrace) {
        this.thread = thread;
        this.stackTrace = stackTrace;
    }

    public boolean isInterrupted() {
        return thread.isInterrupted();
    }

    public boolean isAlive() {
        return thread.isAlive();
    }

    public int getPriority() {
        return thread.getPriority();
    }

    public String getName() {
        return thread.getName();
    }

    public ThreadGroupInfo getThreadGroup() {
        ThreadGroup group = thread.getThreadGroup();
        return group == null ? null : new ThreadGroupInfo(group);
    }

    @Deprecated
    public int getStackFrameCount() {
        try {
            return thread.countStackFrames();
        } catch (IllegalThreadStateException e) {
            return -1;
        }
    }

    public boolean isDaemon() {
        return thread.isDaemon();
    }

    public String getString() {
        return thread.toString();
    }

    public String getContextClassLoaderName() {
        ClassLoader ccl = thread.getContextClassLoader();
        if (ccl == null)
            return null;
        return ccl.getClass().getName();
    }

    public StackTraceInfo getStackTrace() {
        StackTraceElement[] stackTrace = this.stackTrace;
        if (stackTrace == null)
            stackTrace = thread.getStackTrace();
        return new StackTraceInfo(stackTrace);
    }

    @Deprecated
    public long getId() {
        return thread.getId();
    }

    public String getState() {
        return thread.getState().name();
    }

    public String getUncaughtExceptionHandler() {
        UncaughtExceptionHandler handler = thread.getUncaughtExceptionHandler();
        if (handler == null)
            return null;
        return handler.toString();
    }

}
