package net.bodz.bas.site.ajax;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonVerbatimBuf;
import net.bodz.bas.html.io.BHtmlOut;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

@Deprecated
public class AjaxResult
        extends AbstractJsonResult<AjaxResult> {

    static final Logger logger = LoggerFactory.getLogger(AjaxResult.class);

    Map<String, Object> headers;

    Map<String, IHtmlOut> htmlUpdates;
    HtmlOutputFormat htmlOutputFormat;

    public AjaxResult() {
        this(false);
    }

    public AjaxResult(Boolean sort) {
        super(0, null);
        if (sort == null) {
            headers = new HashMap<String, Object>();
            htmlUpdates = new HashMap<String, IHtmlOut>();
        } else if (sort) {
            headers = new TreeMap<String, Object>();
            htmlUpdates = new TreeMap<String, IHtmlOut>();
        } else {
            headers = new LinkedHashMap<String, Object>();
            htmlUpdates = new LinkedHashMap<String, IHtmlOut>();
        }
        htmlOutputFormat = new HtmlOutputFormat();
    }

    public AjaxResult set(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public JsonWriter begin(String key) {
        StringWriter buf = new StringWriter();
        headers.put(key, new JsonVerbatimBuf(buf));
        return new JsonWriter(buf);
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
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);

        if (!headers.isEmpty()) {
            out.key("data");
            out.object();
            for (Map.Entry<String, ?> entry : headers.entrySet()) {
                Object value = entry.getValue();
                if (value == null)
                    continue;
                out.key(entry.getKey());
                if (value instanceof IJsonSerializable) {
                    IJsonSerializable child = (IJsonSerializable) value;
                    out.object();
                    child.writeObject(out);
                    out.endObject();
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
    }

}
