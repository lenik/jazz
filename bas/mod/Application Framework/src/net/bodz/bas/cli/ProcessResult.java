package net.bodz.bas.cli;

public class ProcessResult {

    protected String[] tags;

    public Boolean     changed;
    public boolean     saved;
    public boolean     error;
    public Throwable   cause;

    public ProcessResult(Boolean changed, String... tags) {
        this.changed = changed;
        this.tags = tags;
    }

    public ProcessResult(Throwable cause, String... tags) {
        this.error = true;
        this.cause = cause;
        this.tags = tags;
    }

    public void saved() {
        this.saved = true;
    }

    public void error(Throwable cause) {
        this.error = true;
        this.cause = cause;
    }

    public static ProcessResult autodiff(String... tags) {
        return new ProcessResult((Boolean) null, tags);
    }

    public static ProcessResult changed(String... tags) {
        return new ProcessResult(true, tags);
    }

    public static ProcessResult unchanged(String... tags) {
        return new ProcessResult(false, tags);
    }

    public static ProcessResult error(Throwable cause, String... tags) {
        return new ProcessResult(cause, tags);
    }

}
