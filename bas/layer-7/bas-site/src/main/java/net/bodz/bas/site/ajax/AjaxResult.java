package net.bodz.bas.site.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.RecHtmlOut;
import net.bodz.bas.io.BCharOut;

public class AjaxResult
        extends LinkedHashMap<String, Object>
        implements IJsonSerializable {

    private static final long serialVersionUID = 1L;

    HtmlOutputFormat htmlOutputFormat;
    boolean success = false;
    List<AjaxMessage> messages;
    Map<String, BCharOut> updates;
    Map<String, IHtmlOut> updatehtmls;

    public AjaxResult() {
        htmlOutputFormat = new HtmlOutputFormat();
        messages = new ArrayList<>();
        updates = new HashMap<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AjaxResult success() {
        success = true;
        return this;
    }

    public AjaxMessage message(String type) {
        AjaxMessage message = new AjaxMessage(type);
        messages.add(message);
        return message;
    }

    public AjaxMessage msgbox() {
        return message(AjaxMessage.MSGBOX);
    }

    public AjaxMessage notice() {
        return message(AjaxMessage.NOTICE);
    }

    public AjaxMessage inline() {
        return message(AjaxMessage.INLINE);
    }

    public AjaxMessage log() {
        return message(AjaxMessage.LOG);
    }

    public IHtmlOut update(String id) {
        IHtmlOut out = updatehtmls.get(id);
        if (out == null) {
            BCharOut buf = new BCharOut();
            HtmlDoc doc = new HtmlDoc(buf, htmlOutputFormat);
            out = new RecHtmlOut(doc);
            updates.put(id, buf);
            updatehtmls.put(id, out);
        }
        return out;
    }

    @Override
    public void readObject(JSONObject json)
            throws ParseException {
    }

    @Override
    public void writeObject(JSONWriter out) {
        out.object();
        out.key("success");
        out.value(success);

        if (!messages.isEmpty()) {
            out.key("messages");
            out.array();
            for (AjaxMessage message : messages)
                message.writeObject(out);
            out.endArray();
        }

        if (!isEmpty()) {
            out.key("data");
            out.object();
            for (Map.Entry<String, ?> entry : entrySet()) {
                Object value = entry.getValue();
                if (value == null)
                    continue;
                out.key(entry.getKey());
                out.value(value);
            }
            out.endObject();
        }

        if (!updates.isEmpty()) {
            for (IHtmlOut html : updatehtmls.values())
                html.flush();
            out.key("updates");
            out.object();
            for (Map.Entry<String, BCharOut> entry : updates.entrySet()) {
                out.key(entry.getKey());
                BCharOut html = entry.getValue();
                out.value(html);
            }
            out.endObject();
        }
        out.endObject();
    }

}