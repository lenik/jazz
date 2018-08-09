package net.bodz.bas.site.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.html.io.BHtmlOut;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.io.BTreeOut;

public class AjaxResult
        extends BTreeOut
        implements IJsonSerializable {

    Boolean success;
    int status;
    StringBuffer message = new StringBuffer(100);
    Throwable exception;

    Map<String, Object> fields;

    Map<String, IHtmlOut> htmlUpdates;
    HtmlOutputFormat htmlOutputFormat;

    public AjaxResult() {
        this(false);
    }

    public AjaxResult(Boolean sort) {
        if (sort == null) {
            fields = new HashMap<>();
            htmlUpdates = new HashMap<>();
        } else if (sort) {
            fields = new TreeMap<>();
            htmlUpdates = new TreeMap<>();
        } else {
            fields = new LinkedHashMap<>();
            htmlUpdates = new LinkedHashMap<>();
        }
        htmlOutputFormat = new HtmlOutputFormat();
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

    public AjaxResult succeed() {
        return succeed(0);
    }

    public AjaxResult succeed(int status) {
        success = true;
        this.status = status;
        return this;
    }

    public AjaxResult fail() {
        return fail(0, null);
    }

    public AjaxResult fail(int status) {
        return fail(status, null);
    }

    public AjaxResult fail(String messageFormat, Object... args) {
        return fail(String.format(messageFormat, args));
    }

    public AjaxResult fail(String message) {
        return fail(0, message);
    }

    public AjaxResult fail(int status, String messageFormat, Object... args) {
        return fail(status, String.format(messageFormat, args));
    }

    public AjaxResult fail(int status, String message) {
        success = false;
        this.status = status;
        if (message != null)
            this.println(message);
        return this;
    }

    public AjaxResult fail(Throwable e, String messageFormat, Object... args) {
        return fail(e, String.format(messageFormat, args));
    }

    public AjaxResult fail(Throwable e, String message) {
        return fail(e, 0, message);
    }

    public AjaxResult fail(Throwable e, int status, String messageFormat, Object... args) {
        return fail(e, status, String.format(messageFormat, args));
    }

    public AjaxResult fail(Throwable e, int status, String message) {
        this.exception = e;
        return fail(message);
    }

    public AjaxResult set(String key, Object value) {
        fields.put(key, value);
        return this;
    }

    public IHtmlOut newUpdate(String id) {
        IHtmlOut out = htmlUpdates.get(id);
        if (out == null) {
            BHtmlOut htmlOut = new BHtmlOut(htmlOutputFormat);
            htmlUpdates.put(id, htmlOut);
        }
        return out;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.object();
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

        if (!fields.isEmpty()) {
            out.key("data");
            out.object();
            for (Map.Entry<String, ?> entry : fields.entrySet()) {
                Object value = entry.getValue();
                if (value == null)
                    continue;
                out.key(entry.getKey());
                if (value instanceof IJsonSerializable) {
                    IJsonSerializable child = (IJsonSerializable) value;
                    child.writeObject(out);
                } else {
                    out.value(value);
                }
            }
            out.endObject();
        }

        if (!htmlUpdates.isEmpty()) {
            for (IHtmlOut html : htmlUpdates.values())
                html.flush();
            out.key("updates");
            out.object();
            for (Map.Entry<String, IHtmlOut> entry : htmlUpdates.entrySet()) {
                out.key(entry.getKey());
                IHtmlOut htmlOut = entry.getValue();
                String html = htmlOut.toString();
                out.value(html);
            }
            out.endObject();
        }
        out.endObject();
    }

}
