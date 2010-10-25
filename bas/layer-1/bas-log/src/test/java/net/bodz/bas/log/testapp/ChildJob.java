package net.bodz.bas.log.testapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.bodz.bas.log.ILogComposite;
import net.bodz.bas.log.ILogSink;

public class ChildJob {

    private ParentJob parent;
    private String name;

    private ILogComposite log;

    public ChildJob(ParentJob parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public ILogComposite getLogger() {
        if (log == null)
            throw new IllegalStateException("Log doesn't configured");
        return log;
    }

    public void setLogger(ILogComposite log) {
        if (log == null)
            throw new NullPointerException("log");
        this.log = log;
    }

    public ParentJob getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void exec() {
        ILogSink info = getLogger().getInfoSink();

        info._("Loading...   ");
        try {
            new FileInputStream("/!@#$Not-exist file!");
        } catch (FileNotFoundException e) {
            log.getErrorSink().p("Open failed", e);
        }

        info._("Child execute...");

        parent.query("query from child");

        info.p("Done!");
    }

}
