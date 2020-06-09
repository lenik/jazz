package net.bodz.bas.site.json;

import java.io.IOException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

@SuppressWarnings("unchecked")
public class AbstractJsonResponse<self_t>
        implements IJsonSerializable {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonResponse.class);

    public static final int OK = 0;
    public static final int WARN = 300;
    public static final int ERROR = 400;
    public static final int FATAL_ERROR = 500;

    int status;
    String message;
    Throwable exception;
    Object data;

    public AbstractJsonResponse() {
    }

    public AbstractJsonResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return status < ERROR;
    }

    public boolean isError() {
        return status >= ERROR;
    }

    public self_t succeed() {
        this.status = OK;
        return (self_t) this;
    }

    public self_t fail() {
        return fail(ERROR, null);
    }

    public self_t fail(int status) {
        return fail(status, null);
    }

    public self_t fail(String messageFormat, Object... args) {
        return fail(String.format(messageFormat, args));
    }

    public self_t fail(String message) {
        return fail(ERROR, message);
    }

    public self_t fail(int status, String messageFormat, Object... args) {
        return fail(status, String.format(messageFormat, args));
    }

    public self_t fail(int status, String message) {
        this.status = status;
        this.message = message;
        return (self_t) this;
    }

    public self_t fail(Throwable e) {
        return fail(e, e.getMessage());
    }

    public self_t fail(Throwable e, int status) {
        return fail(e, e.getMessage(), status);
    }

    public self_t fail(Throwable e, String messageFormat, Object... args) {
        return fail(e, String.format(messageFormat, args));
    }

    public self_t fail(Throwable e, String message) {
        return fail(e, ERROR, message);
    }

    public self_t fail(Throwable e, int status, String messageFormat, Object... args) {
        return fail(e, status, String.format(messageFormat, args));
    }

    public self_t fail(Throwable e, int status, String message) {
        logger.warn(e, "ajax failed with: " + message);
        this.exception = e;
        return fail(message);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("status", status);
        if (status < ERROR)
            out.entry("success", true);
        if (status >= WARN)
            out.entry("warn", true);
        if (status >= ERROR)
            out.entry("failed", true);

        if (message != null)
            out.entry("message", message);

        if (exception != null) {
            out.key("exception");
            out.object();
            out.entry("type", exception.getClass().getName());
            out.entry("message", exception.getMessage());
            // StackTraceElement[] trace = exception.getStackTrace();
            out.endObject();
        }

        if (data != null)
            out.entry("data", data);
    }

}
