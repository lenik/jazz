package net.bodz.bas.sms;

import java.io.IOException;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonStruct;

public class SmsRecord
        extends JsonStruct {

    private static final long serialVersionUID = 1L;

    public String uid;
    public String recipient;
    public String templateName;
    public String text;
    public List<Object> parameters;
    public ISmsSendResponse response;

    public SmsRecord() {
    }

    public SmsRecord(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
    }

    public SmsRecord(String recipient, String templateName, List<Object> parameters) {
        this.recipient = recipient;
        this.templateName = templateName;
        this.parameters = parameters;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        recipient = o.getString("recipient", recipient);
        templateName = o.getString("templateName", templateName);
        text = o.getString("text", text);
        List<String> params = o.getStrings("parameters", null);
        if (params != null) {
            parameters.clear();
            parameters.addAll(params);
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("recipient", recipient);
        out.entry("templateName", templateName);
        out.entry("text", text);
        if (parameters != null) {
            out.key("parameters");
            out.array();
            for (Object param : parameters)
                out.value(param);
            out.endArray();
        }
        out.entryNotNull("response", response);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public String[] getParametersAsStringArray() {
        int len = parameters == null ? 0 : parameters.size();
        String[] array = new String[len];
        for (int i = 0; i < len; i++) {
            Object param = parameters.get(i);
            array[i] = param == null ? null : param.toString();
        }
        return array;
    }

    public ISmsSendResponse getResponse() {
        return response;
    }

    public void setResponse(ISmsSendResponse response) {
        this.response = response;
    }

}
