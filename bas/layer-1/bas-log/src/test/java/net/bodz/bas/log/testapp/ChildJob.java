package net.bodz.bas.log.testapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.bodz.bas.log.ILogLayer;
import net.bodz.bas.log.ILogSink;

public class ChildJob {

    private ParentJob parent;
    private String name;

    private ILogLayer logLayer;

    public ChildJob(ParentJob parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public ILogLayer getLogLayer() {
        if (logLayer == null)
            throw new IllegalStateException("Log Layer doesn't configured");
        return logLayer;
    }

    public void setLogLayer(ILogLayer logLayer) {
        if (logLayer == null)
            throw new NullPointerException("logLayer");
        this.logLayer = logLayer;
    }

    public ParentJob getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void exec() {
        ILogSink info = getLogLayer().getInfoSink();

        info.p_("Loading...   ");
        try {
            new FileInputStream("/!@#$Not-exist file!");
        } catch (FileNotFoundException e) {
            logLayer.getErrorSink().p("Open failed", e);
        }

        info.p_("Child execute...");

        parent.query("query from child");

        info.p("Done!");
    }

}
