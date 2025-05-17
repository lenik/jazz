package net.bodz.bas.site.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.BufferedLogger;
import net.bodz.bas.log.LogLevel;
import net.bodz.bas.log.LogRecord;
import net.bodz.bas.meta.decl.NotNull;

public class JsonLogger
        extends BufferedLogger
        implements
            IJsonForm {

    public JsonLogger() {
        super();
    }

    public JsonLogger(String prefix, int maxRecordCount) {
        super(prefix, maxRecordCount);
    }

    public JsonLogger(String prefix) {
        super(prefix);
    }

    private static final String K_PREFIX = "prefix";
    private static final String K_RECORDS = "records";

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        // setPrefix(o.getString(K_PREFIX));

        JsonArray j_records = o.getJsonArray(K_RECORDS);
        int n = j_records.size();
        for (int i = 0; i < n; i++) {
            getRecords().add(jsonIn_LogRecord(j_records.getJsonObject(i)));
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_PREFIX, getPrefix());

        out.key(K_RECORDS);
        out.array();
        for (LogRecord record : getRecords())
            jsonOut_LogRecord(out, record);
        out.endArray();
    }

    private static final String K_TIME = "time";
    private static final String K_LEVEL = "level";
    private static final String K_DELTA = "delta";
    // private static final String K_SOURCE = "source";
    private static final String K_MESSAGE = "message";
    private static final String K_EXCEPTION = "exception";

    static LogRecord jsonIn_LogRecord(JsonObject o) {
        String _level = o.getString(K_LEVEL);
        LogLevel level = LogLevel.parse(_level);

        int delta = o.getInt(K_DELTA);
        String message = o.getString(K_MESSAGE);
        // Throwable exception = o.getThrowable(K_EXCEPTION);

        LogRecord record = new LogRecord(level, delta, message);
        record.time = o.getLong(K_TIME, record.time);

        return record;
    }

    static void jsonOut_LogRecord(IJsonOut out, LogRecord record) {
        out.object();
        out.entryNot0(K_TIME, record.time);
        out.entryNotNull(K_LEVEL, record.level);
        out.entryNot0(K_DELTA, record.delta);
        // out.entryNotNull(K_SOURCE, record.source);
        if (record.message != null)
            out.entry(K_MESSAGE, record.message.toString());
        out.entryNotNull(K_EXCEPTION, record.exception);
        out.endObject();
    }

}
