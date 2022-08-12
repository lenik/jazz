package net.bodz.bas.c.org.json;

import java.sql.SQLException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;

public class JsonStr
        implements
            IJsonStrForm {

    String json;

    public JsonStr() {
    }

    public JsonStr(String json) {
        this.json = json;
    }

    @Override
    public String toJsonStr(JsonFormOptions opts) {
        return json;
    }

    @Override
    public void fromJsonStr(String jsonStr, JsonFormOptions opts)
            throws ParseException {
        this.json = jsonStr;
    }

    public void fromJsonStr_sql(String jsonStr, JsonFormOptions opts)
            throws SQLException {
        try {
            fromJsonStr(jsonStr, opts);
        } catch (ParseException e) {
            throw new SQLException(String.format(//
                    "Failed to parse: %s, json: %s", e.getMessage(), json), e);
        }
    }

}
