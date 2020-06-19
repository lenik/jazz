package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonVerbatimBuf;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.log.impl.BufferedLogger;

@SuppressWarnings("unchecked")
public class AbstractJsonResponse<self_t>
        implements IJsonSerializable {

    public static final int OK = 0;
    public static final int WARN = 300;
    public static final int ERROR = 400;
    public static final int FATAL_ERROR = 500;

    int status = OK;
    String message;
    Throwable exception;
    Object data;

    private Boolean headerOrder;
    private Map<String, Object> headers;
    private BufferedLogger logger = new BufferedLogger();

    public AbstractJsonResponse() {
    }

    public AbstractJsonResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public AbstractJsonResponse(AbstractJsonResponse<?> o) {
        this.status = o.status;
        this.message = o.message;
        this.exception = o.exception;
        this.data = o.data;
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

    public Boolean getHeaderOrder() {
        return headerOrder;
    }

    public synchronized void setHeaderOrder(Boolean headerOrder) {
        if (this.headerOrder == headerOrder)
            return;
        this.headerOrder = headerOrder;
        if (headers != null) {
            Map<String, Object> newMap = createMap(headerOrder);
            newMap.putAll(headers);
            headers = newMap;
        }
    }

    protected <T> Map<String, T> createMap(Boolean order) {
        if (order == null) {
            return new HashMap<String, T>();
        } else if (order) {
            return new TreeMap<String, T>();
        } else {
            return new LinkedHashMap<String, T>();
        }
    }

    public Map<String, Object> getHeaders() {
        if (headers == null)
            synchronized (this) {
                if (headers == null)
                    headers = createMap(headerOrder);
            }
        return headers;
    }

    public Object get(String header) {
        if (headers == null)
            return null;
        else
            return headers.get(header);
    }

    public self_t set(String header, Object content) {
        getHeaders().put(header, content);
        return (self_t) this;
    }

    public JsonWriter begin(String key) {
        StringWriter buf = new StringWriter();
        set(key, new JsonVerbatimBuf(key, buf));
        return new JsonWriter(buf);
    }

    public ILogger getLogger() {
        return logger;
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

        if (headers != null) {
            out.key("headers");
            out.object();
            for (Map.Entry<String, ?> entry : headers.entrySet()) {
                Object value = entry.getValue();
                if (value == null)
                    continue;
                if (value instanceof JsonVerbatimBuf) {
                    IJsonSerializable child = (IJsonSerializable) value;
                    child.writeObject(out);
                } else {
                    out.key(entry.getKey());
                    out.value(value);
                }
            }
            out.endObject();
        }

        if (data != null)
            out.entry("data", data);
    }

}
