package net.bodz.bas.log.testapp;

import net.bodz.bas.log.ILogComposite;
import net.bodz.bas.log.ILogSink;

public class ParentJob {

    private String name;
    private ILogComposite log;

    public ParentJob(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public ILogComposite getLogger() {
        if (log == null)
            throw new IllegalStateException("Log Layer doesn't configured");
        return log;
    }

    public void setLogger(ILogComposite log) {
        if (log == null)
            throw new NullPointerException("log");
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void exec() {
        ILogSink info = getLogger().getInfoSink();
        ILogSink more = getLogger().getInfoSink(ILogComposite.LEVEL_LOW);
        info.p("Start");
        more.p("Parameter info...");
        // ChildJob child = new ChildJob(this, "child-1");
        // child.exec();
        info.p("End");
    }

    public String query(String question) {
        ILogSink debug = getLogger().getDebugSink();
        debug.p("Query() called: " + question);
        return "answer";
    }

}
