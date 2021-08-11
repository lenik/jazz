package net.bodz.fork.org.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

/**
 * This provides static methods to convert comma delimited text into a
 * JsonArray, and to convert a JsonArray into comma delimited text. Comma
 * delimited text is a very popular format for data interchange. It is
 * understood by most database, spreadsheet, and organizer programs.
 * <p>
 * Each row of text represents a row in a table or a data record. Each row
 * ends with a NEWLINE character. Each row contains one or more values.
 * Values are separated by commas. A value can contain any character except
 * for comma, unless is is wrapped in single quotes or double quotes.
 * <p>
 * The first row usually contains the names of the columns.
 * <p>
 * A comma delimited list can be converted into a JsonArray of JSONObjects.
 * The names for the elements in the JSONObjects can be taken from the names
 * in the first row.
 * @author JSON.org
 * @version 2016-05-01
 */
public class CDL {

    /**
     * Get the next value. The value can be wrapped in quotes. The value can
     * be empty.
     * @param x A JSONTokener of the source text.
     * @return The value string, or null if empty.
     * @throws JSONException if the quoted string is badly formed.
     */
    private static String getValue(JSONTokener x) throws JSONException {
        char c;
        char q;
        StringBuilder sb;
        do {
            c = x.next();
        } while (c == ' ' || c == '\t');
        switch (c) {
        case 0:
            return null;
        case '"':
        case '\'':
            q = c;
            sb = new StringBuilder();
            for (;;) {
                c = x.next();
                if (c == q) {
                    //Handle escaped double-quote
                    char nextC = x.next();
                    if(nextC != '\"') {
                        // if our quote was the end of the file, don't step
                        if(nextC > 0) {
                            x.back();
                        }
                        break;
                    }
                }
                if (c == 0 || c == '\n' || c == '\r') {
                    throw x.syntaxError("Missing close quote '" + q + "'.");
                }
                sb.append(c);
            }
            return sb.toString();
        case ',':
            x.back();
            return "";
        default:
            x.back();
            return x.nextTo(',');
        }
    }

    /**
     * Produce a JsonArray of strings from a row of comma delimited values.
     * @param x A JSONTokener of the source text.
     * @return A JsonArray of strings.
     * @throws JSONException
     */
    public static JsonArray rowToJSONArray(JSONTokener x) throws JSONException {
        JsonArray ja = new JsonArray();
        for (;;) {
            String value = getValue(x);
            char c = x.next();
            if (value == null ||
                    (ja.length() == 0 && value.length() == 0 && c != ',')) {
                return null;
            }
            ja.put(value);
            for (;;) {
                if (c == ',') {
                    break;
                }
                if (c != ' ') {
                    if (c == '\n' || c == '\r' || c == 0) {
                        return ja;
                    }
                    throw x.syntaxError("Bad character '" + c + "' (" +
                            (int)c + ").");
                }
                c = x.next();
            }
        }
    }

    /**
     * Produce a JsonObject from a row of comma delimited text, using a
     * parallel JsonArray of strings to provides the names of the elements.
     * @param names A JsonArray of names. This is commonly obtained from the
     *  first row of a comma delimited text file using the rowToJSONArray
     *  method.
     * @param x A JSONTokener of the source text.
     * @return A JsonObject combining the names and values.
     * @throws JSONException
     */
    public static JsonObject rowToJSONObject(JsonArray names, JSONTokener x)
            throws JSONException {
        JsonArray ja = rowToJSONArray(x);
        return ja != null ? ja.toJsonObject(names) :  null;
    }

    /**
     * Produce a comma delimited text row from a JsonArray. Values containing
     * the comma character will be quoted. Troublesome characters may be
     * removed.
     * @param ja A JsonArray of strings.
     * @return A string ending in NEWLINE.
     */
    public static String rowToString(_JSONArray ja) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ja.length(); i += 1) {
            if (i > 0) {
                sb.append(',');
            }
            Object object = ja.get(i);
            if (object != null) {
                String string = object.toString();
                if (string.length() > 0 && (string.indexOf(',') >= 0 ||
                        string.indexOf('\n') >= 0 || string.indexOf('\r') >= 0 ||
                        string.indexOf(0) >= 0 || string.charAt(0) == '"')) {
                    sb.append('"');
                    int length = string.length();
                    for (int j = 0; j < length; j += 1) {
                        char c = string.charAt(j);
                        if (c >= ' ' && c != '"') {
                            sb.append(c);
                        }
                    }
                    sb.append('"');
                } else {
                    sb.append(string);
                }
            }
        }
        sb.append('\n');
        return sb.toString();
    }

    /**
     * Produce a JsonArray of JSONObjects from a comma delimited text string,
     * using the first row as a source of names.
     * @param string The comma delimited text.
     * @return A JsonArray of JSONObjects.
     * @throws JSONException
     */
    public static JsonArray toJSONArray(String string) throws JSONException {
        return toJSONArray(new JSONTokener(string));
    }

    /**
     * Produce a JsonArray of JSONObjects from a comma delimited text string,
     * using the first row as a source of names.
     * @param x The JSONTokener containing the comma delimited text.
     * @return A JsonArray of JSONObjects.
     * @throws JSONException
     */
    public static JsonArray toJSONArray(JSONTokener x) throws JSONException {
        return toJSONArray(rowToJSONArray(x), x);
    }

    /**
     * Produce a JsonArray of JSONObjects from a comma delimited text string
     * using a supplied JsonArray as the source of element names.
     * @param names A JsonArray of strings.
     * @param string The comma delimited text.
     * @return A JsonArray of JSONObjects.
     * @throws JSONException
     */
    public static JsonArray toJSONArray(JsonArray names, String string)
            throws JSONException {
        return toJSONArray(names, new JSONTokener(string));
    }

    /**
     * Produce a JsonArray of JSONObjects from a comma delimited text string
     * using a supplied JsonArray as the source of element names.
     * @param names A JsonArray of strings.
     * @param x A JSONTokener of the source text.
     * @return A JsonArray of JSONObjects.
     * @throws JSONException
     */
    public static JsonArray toJSONArray(JsonArray names, JSONTokener x)
            throws JSONException {
        if (names == null || names.length() == 0) {
            return null;
        }
        JsonArray ja = new JsonArray();
        for (;;) {
            JsonObject jo = rowToJSONObject(names, x);
            if (jo == null) {
                break;
            }
            ja.put(jo);
        }
        if (ja.length() == 0) {
            return null;
        }
        return ja;
    }


    /**
     * Produce a comma delimited text from a JsonArray of JSONObjects. The
     * first row will be a list of names obtained by inspecting the first
     * JsonObject.
     * @param ja A JsonArray of JSONObjects.
     * @return A comma delimited text.
     * @throws JSONException
     */
    public static String toString(JsonArray ja) throws JSONException {
        JsonObject jo = ja.getJsonObject(0);
        if (jo != null) {
            JsonArray names = jo.names();
            if (names != null) {
                return rowToString(names) + toString(names, ja);
            }
        }
        return null;
    }

    /**
     * Produce a comma delimited text from a JsonArray of JSONObjects using
     * a provided list of names. The list of names is not included in the
     * output.
     * @param names A JsonArray of strings.
     * @param ja A JsonArray of JSONObjects.
     * @return A comma delimited text.
     * @throws JSONException
     */
    public static String toString(JsonArray names, JsonArray ja)
            throws JSONException {
        if (names == null || names.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ja.length(); i += 1) {
            JsonObject jo = ja.getJsonObject(i);
            if (jo != null) {
                sb.append(rowToString(jo.toJSONArray(names)));
            }
        }
        return sb.toString();
    }
}
