package net.bodz.sms;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;

public class SmsRecord
        implements IJsonSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    public String recipient;
    public String preparedId;
    public String text;
    public List<String> parameters;

    public SmsRecord() {
    }

    public SmsRecord(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
    }

    public SmsRecord(String recipient, String preparedId, List<String> parameters) {
        this.recipient = recipient;
        this.preparedId = preparedId;
        this.parameters = parameters;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        recipient = o.getString("recipient", recipient);
        preparedId = o.getString("preparedId", preparedId);
        text = o.getString("text", text);
        parameters = o.getStrings("parameters", parameters);

    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("recipient", recipient);
        out.entry("preparedId", preparedId);
        out.entry("text", text);
        if (parameters != null) {
            out.key("parameters");
            out.array();
            for (String param : parameters)
                out.value(param);
            out.endArray();
        }
    }

}
