package user.logs.weave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.Logger;

public class ChildJob {

    private ParentJob parent;
    private String name;

    private Logger logger;

    public ChildJob(ParentJob parent, String name) {
        this.parent = parent;
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
            logger.getErrorSink().p("Open failed", e);
        }

        info._("Child execute...");

        parent.query("query from child");

        info.p("Done!");
    }

}
