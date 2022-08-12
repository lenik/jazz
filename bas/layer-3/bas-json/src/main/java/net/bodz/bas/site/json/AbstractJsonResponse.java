package net.bodz.bas.site.json;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVerbatimBuf_Scalar;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.BufferedLogger;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.form.SortOrder;

@SuppressWarnings("unchecked")
public class AbstractJsonResponse<self_t extends IMutableJsonResponse>
        implements
            IMutableJsonResponse {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonResponse.class);

    public static final String sectionsRoot = "root";

    int status = OK;
    String message;
    Throwable exception;
    Object data = null;

    private SortOrder headerOrder = SortOrder.NONE;
    private Map<String, Object> headers;
    private BufferedLogger logbuf = new BufferedLogger();

    public AbstractJsonResponse() {
    }

    public AbstractJsonResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public AbstractJsonResponse(AbstractJsonResponse<?> o) {
        this(o, false);
    }

    public AbstractJsonResponse(AbstractJsonResponse<?> o, boolean shallowCopy) {
        this.status = o.status;
        this.message = o.message;
        this.exception = o.exception;
        this.data = o.data;

        this.headerOrder = o.headerOrder;
        if (shallowCopy) {
            this.headers = o.headers;
            this.logbuf = o.logbuf;
        } else {
            this.headers = headerOrder.newMap();
            this.headers.putAll(o.headers);
            this.logbuf.getRecords().addAll(o.logbuf.getRecords());
        }
    }

    @Override
    public boolean isSuccess() {
        return status < ERROR;
    }

    @Override
    public boolean isError() {
        return status >= ERROR;
    }

    @Override
    public self_t succeed() {
        this.status = OK;
        return (self_t) this;
    }

    @Override
    public self_t fail() {
        return fail(ERROR, null);
    }

    @Override
    public self_t fail(int status) {
        return fail(status, null);
    }

    @Override
    public self_t fail(String messageFormat, Object... args) {
        return fail(String.format(messageFormat, args));
    }

    @Override
    public self_t fail(String message) {
        return fail(ERROR, message);
    }

    @Override
    public self_t fail(int status, String messageFormat, Object... args) {
        return fail(status, String.format(messageFormat, args));
    }

    @Override
    public self_t fail(int status, String message) {
        this.status = status;
        this.message = message;
        return (self_t) this;
    }

    @Override
    public self_t fail(Throwable e) {
        return fail(e, e.getMessage());
    }

    @Override
    public self_t fail(Throwable e, int status) {
        return fail(e, e.getMessage(), status);
    }

    @Override
    public self_t fail(Throwable e, String messageFormat, Object... args) {
        return fail(e, String.format(messageFormat, args));
    }

    @Override
    public self_t fail(Throwable e, String message) {
        return fail(e, ERROR, message);
    }

    @Override
    public self_t fail(Throwable e, int status, String messageFormat, Object... args) {
        return fail(e, status, String.format(messageFormat, args));
    }

    @Override
    public self_t fail(Throwable e, int status, String message) {
        logger.error(e, "A json failure detected: " + message);
        logbuf.warn(e, "ajax failed with: " + message);
        this.exception = e;
        return fail(message);
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object js_val) {
        this.data = js_val;
    }

    @Override
    public SortOrder getHeaderOrder() {
        return headerOrder;
    }

    @Override
    public synchronized void setHeaderOrder(SortOrder headerOrder) {
        if (this.headerOrder == headerOrder)
            return;
        this.headerOrder = headerOrder;
        if (headers != null) {
            Map<String, Object> newMap = headerOrder.newMap();
            newMap.putAll(headers);
            headers = newMap;
        }
    }

    @Override
    public Map<String, Object> getHeaders() {
        if (headers == null)
            synchronized (this) {
                if (headers == null)
                    headers = headerOrder.newMap();
            }
        return headers;
    }

    @Override
    public Object getHeader(String header) {
        if (headers == null)
            return null;
        else
            return headers.get(header);
    }

    @Override
    public self_t setHeader(String header, Object content) {
        getHeaders().put(header, content);
        return (self_t) this;
    }

    /**
     * Begins a new section.
     */
    @Override
    public JsonWriter begin(String key) {
        StringBuilder buf = new StringBuilder();
        setHeader(key, new JsonVerbatimBuf_Scalar(buf));
        return new JsonWriter(buf);
    }

    @Override
    public synchronized JsonSection getRoot() {
        JsonSection root = (JsonSection) getHeader(sectionsRoot);
        if (root == null) {
            root = new JsonSection();
            setHeader(sectionsRoot, root);
        }
        return root;
    }

    @Override
    public ILogger getLogger() {
        return logbuf;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        for (String key : o.keySet()) {
            boolean handled = readRootEntry(o, key, opts);
            if (handled)
                continue;
            Object val = o.get(key);
            if (val instanceof JsonObject) {
                JsonObject node = (JsonObject) val;
                JsonSection section = new JsonSection();
                section.jsonIn(node, opts);
                setHeader(key, section);
            } else {
                setHeader(key, val);
            }
        }
    }

    protected boolean readRootEntry(JsonObject o, String key, JsonFormOptions opts)
            throws ParseException {
        if ("success".equals(key) || "warn".equals(key) || "failed".equals(key))
            return true;

        if ("status".equals(key)) {
            status = o.getInt("status");
            return true;
        }

        if ("message".equals(key)) {
            message = o.getString("message");
            return true;
        }

        if ("exception".equals(key)) {
            JsonObject ex = o.getJsonObject("exception");
            String exClassName = ex.getString("type");
            String message = ex.getString("message");
            try {
                Class<?> exType = Class.forName(exClassName);
                if (Throwable.class.isAssignableFrom(exType)) {
                    Constructor<?> ctor = exType.getConstructor(String.class);
                    this.exception = (Throwable) ctor.newInstance(message);
                } else {
                    // assert false.
                    logger.error("Invalid exception class: " + exType);
                }
            } catch (ReflectiveOperationException e) {
                logger.error("Can't re-instantiate the exception: " + e.getMessage(), e);
            }
            return true;
        }

        if ("data".equals(key))
            return true;

        if ("dataType".equals(key)) {
            String dataTypeName = o.getString("dataType");
            IJsonForm data;
            try {
                Class<?> dataType = Class.forName(dataTypeName);
                if (IJsonForm.class.isAssignableFrom(dataType))
                    data = (IJsonForm) dataType.newInstance();
                else
                    throw new ParseException("Unsupported data type: " + dataTypeName);
            } catch (ReflectiveOperationException e) {
                throw new ParseException("Can't instantiate data of " + dataTypeName, e);
            }
            JsonObject dataNode = o.getJsonObject("data");
            data.jsonIn(dataNode, opts);
            this.data = data;
            return true;
        }

        return false;
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
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
            for (Map.Entry<String, ?> entry : headers.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value == null)
                    continue;

                if (value instanceof IJsonForm) {
                    IJsonForm jsonForm = (IJsonForm) value;
                    out.key(key);
                    jsonForm.jsonOut(out, opts, true);
                } else {
                    out.entry(key, value);
                    // out.key(key);
                    // out.object(value);
                }
            }
        } // headers

        if (data != null) {
            out.entry("dataType", data.getClass());
            out.entry("data", data);
        }
    }

    @Override
    public String toString() {
        try {
            return JsonFn.toJson(this, JsonFormOptions.PRETTY);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
