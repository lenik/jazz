package net.bodz.bas.site.ajax;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import net.bodz.bas.err.LoadException;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.io.res.builtin.ReaderSource;
import net.bodz.bas.io.res.tools.StreamReading;

public class HttpPayload {

    static String prefix = HttpPayload.class.getName();
    public static String JSON_ATTRIBUTE_NAME = prefix + ".json";

    public static JsonObject getJsonPayload(HttpServletRequest req) {
        return getJsonPayload(req, "data");
    }

    public static synchronized JsonObject getJsonPayload(HttpServletRequest req, String altParam)
            throws LoadException, IllegalStateException {
        JsonObject jsonObj = (JsonObject) req.getAttribute(JSON_ATTRIBUTE_NAME);
        if (jsonObj == null) {
            try {
                String json = null;
                if (altParam != null)
                    json = req.getParameter(altParam);
                if (json == null)
                    try {
                        BufferedReader reader = req.getReader();
                        json = new ReaderSource(reader).to(StreamReading.class).readString();
                    } catch (IOException e) {
                        throw new LoadException("Failed to read request payload: " + e.getMessage(), e);
                    }
                jsonObj = new JsonObject(json);
                req.setAttribute(JSON_ATTRIBUTE_NAME, jsonObj);
                return jsonObj;
            } catch (JSONException e) {
                throw new LoadException("Failed to parse json: " + e.getMessage(), e);
            }
        }
        return jsonObj;
    }
}
