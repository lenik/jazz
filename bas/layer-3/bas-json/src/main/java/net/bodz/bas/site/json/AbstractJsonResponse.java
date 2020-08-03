package net.bodz.bas.site.json;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOptions;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonVerbatimBuf;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.log.impl.BufferedLogger;

@SuppressWarnings("unchecked")
public class AbstractJsonResponse<self_t>
        implements IJsonSerializable {

    static final Logger logger = LoggerFactory.getLogger(AbstractJsonResponse.class);

    public static final int OK = 0;
    public static final int WARN = 300;
    public static final int ERROR = 400;
    public static final int FATAL_ERROR = 500;

    public static final String sectionsRoot = "root";

    int status = OK;
    String message;
    Throwable exception;
    IJsonSerializable data = null;

    private Boolean headerOrder;
    private Map<String, Object> headers;
    private BufferedLogger logbuf = new BufferedLogger();

    public AbstractJsonResponse() {
    }

    public AbstractJsonResponse(int status, String message, IJsonSerializable data) {
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
            this.headers = createMap(headerOrder);
            this.headers.putAll(o.headers);
            this.logbuf.getRecords().addAll(o.logbuf.getRecords());
        }
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
        logbuf.warn(e, "ajax failed with: " + message);
        this.exception = e;
        return fail(message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public IJsonSerializable getData() {
        return data;
    }

    public void setData(IJsonSerializable data) {
        this.data = data;
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

    public Object getHeader(String header) {
        if (headers == null)
            return null;
        else
            return headers.get(header);
    }

    public self_t setHeader(String header, Object content) {
        getHeaders().put(header, content);
        return (self_t) this;
    }

    /**
     * Begins a new section.
     */
    public JsonWriter begin(String key) {
        StringWriter buf = new StringWriter();
        setHeader(key, new JsonVerbatimBuf(null, buf));
        return new JsonWriter(buf);
    }

    public synchronized JsonSection getRoot() {
        JsonSection root = (JsonSection) getHeader(sectionsRoot);
        if (root == null) {
            root = new JsonSection();
            setHeader(sectionsRoot, root);
        }
        return root;
    }

    public ILogger getLogger() {
        return logbuf;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        for (String key : o.keySet()) {
            boolean handled = readRootEntry(o, key);
            if (handled)
                continue;
            Object val = o.get(key);
            if (val instanceof JsonObject) {
                JsonObject node = (JsonObject) val;
                JsonSection section = new JsonSection();
                section.readObject(node);
                setHeader(key, section);
            } else {
                setHeader(key, val);
            }
        }
    }

    protected boolean readRootEntry(JsonObject o, String key)
            throws ParseException {
        switch (key) {
        case "success":
        case "warn":
        case "failed":
            return true;
        case "status":
            status = o.getInt("status");
            return true;

        case "message":
            message = o.getString("message");
            return true;

        case "exception":
            JsonObject ex = o.getChild("exception");
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

        case "data":
            return true;
        case "dataType":
            String dataTypeName = o.getString("dataType");
            IJsonSerializable data;
            try {
                Class<?> dataType = Class.forName(dataTypeName);
                if (IJsonSerializable.class.isAssignableFrom(dataType))
                    data = (IJsonSerializable) dataType.newInstance();
                else
                    throw new ParseException("Unsupported data type: " + dataTypeName);
            } catch (ReflectiveOperationException e) {
                throw new ParseException("Can't instantiate data of " + dataTypeName, e);
            }
            JsonObject dataNode = o.getChild("data");
            data.readObject(dataNode);
            this.data = data;
            return true;
        }
        return false;
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
            for (Map.Entry<String, ?> entry : headers.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value == null)
                    continue;

                if (value instanceof IJsonSerializable) {
                    IJsonSerializable jsable = (IJsonSerializable) value;
                    IJsonOptions opts = IJsonOptions.NULL;
                    if (value instanceof IJsonOptions)
                        opts = (IJsonOptions) value;

                    if (opts.isMixedIn()) {
                        jsable.writeObject(out);
                        continue;
                    }

                    out.key(key);
                    if (opts.isSelfContained()) {
                        jsable.writeObject(out);
                    } else {
                        out.object();
                        jsable.writeObject(out);
                        out.endObject();
                    }
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
        return JsonFn.toJson(this);
    }

}
