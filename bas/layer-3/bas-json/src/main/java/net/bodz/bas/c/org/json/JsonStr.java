package net.bodz.bas.c.org.json;

import java.sql.SQLException;

import net.bodz.bas.err.ParseException;

public class JsonStr
        implements
            IJsonForm {

    String json;

    public JsonStr() {
    }

    public JsonStr(String json) {
        this.json = json;
    }

    @Override
    public String toJsonStr() {
        return json;
    }

    @Override
    public void fromJsonStr(String jsonStr)
            throws ParseException {
        this.json = jsonStr;
    }

    public void fromJsonStr_sql(String jsonStr)
            throws SQLException {
        try {
            fromJsonStr(jsonStr);
        } catch (ParseException e) {
            throw new SQLException(String.format(//
                    "Failed to parse: %s, json: %s", e.getMessage(), json), e);
        }
    }

}
