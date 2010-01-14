package net.bodz.bas.log.testapp;

import net.bodz.bas.log.ILogLayer;
import net.bodz.bas.log.ILogSink;

public class ParentJob {

    private String name;
    private ILogLayer logLayer;

    public ParentJob(String name) {
        if (name == null)
            throw new NullPointerException("name");
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

    public String getName() {
        return name;
    }

    public void exec() {
        ILogSink info = getLogLayer().getInfoSink();
        ILogSink more = getLogLayer().getInfoSink(ILogSink.LEVEL_MORE);
        info.p("Start");
        more.p("Parameter info...");
        // ChildJob child = new ChildJob(this, "child-1");
        // child.exec();
        info.p("End");
    }

    public String query(String question) {
        ILogSink debug = getLogLayer().getDebugSink();
        debug.p("Query() called: " + question);
        return "answer";
    }

}
