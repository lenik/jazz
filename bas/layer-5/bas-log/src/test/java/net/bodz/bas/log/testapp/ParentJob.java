package net.bodz.bas.log.testapp;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.api.Logger;

public class ParentJob {

    private String name;
    private Logger logger;

    public ParentJob(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public Logger getLogger() {
        if (logger == null)
            throw new IllegalStateException("Logger doesn't configured");
        return logger;
    }

    public void setLogger(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.logger = logger;
    }

    public String getName() {
        return name;
    }

    public void exec() {
        ILogSink info = getLogger().getInfoSink();
        ILogSink more = getLogger().getInfoSink();
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
