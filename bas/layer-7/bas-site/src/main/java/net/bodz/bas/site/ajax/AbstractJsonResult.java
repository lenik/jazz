package net.bodz.bas.site.ajax;

import java.io.IOException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.io.BTreeOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

@SuppressWarnings("unchecked")
public class AbstractJsonResult<self_t extends AbstractJsonResult<?>>
        extends BTreeOut
        implements IJsonSerializable {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonResult.class);

    public static final int OK = 0;
    public static final int ERROR = -1;

    protected Boolean success;
    int status;
    String message;
    Throwable exception;

    public AbstractJsonResult() {
    }

    public AbstractJsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success == Boolean.TRUE;
    }

    public boolean isFailed() {
        return success == Boolean.FALSE;
    }

    public self_t succeed() {
        return succeed(OK);
    }

    public self_t succeed(int status) {
        success = true;
        this.status = status;
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
        success = false;
        this.status = status;
        if (message != null)
            this.println(message);
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
        if (success != null) {
            out.key("success");
            out.value(success);
            out.key("failed");
            out.value(!success);
        }
        out.key("status");
        out.value(status);

        flush();
        String message = toString();
        out.key("message");
        out.value(message);
    }

}
