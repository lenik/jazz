package net.bodz.bas.crypto.trans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import net.bodz.bas.c.java.util.TimeZones;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.site.json.AbstractJsonResponse;

public class FlyingIndex
        implements
            IFlyingIndex,
            IJsonForm {

    /**
     * Because index can be positive or negative. So use 0L here instead of -1.
     */
    private static final long INDEX_NA = 0L;

    long window;
    long time;
    long index;

    public FlyingIndex(long index, long window, long time) {
        this.index = index;
        this.window = window;
        this.time = time;
    }

    public FlyingIndex(long index, long window) {
        this.index = index;
        this.window = window;
        this.time = window * index;
    }

    FlyingIndex() {
        this.time = -1L;
        this.window = 0;
        this.index = INDEX_NA;
    }

    public static final FlyingIndex NULL = new FlyingIndex();

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public long getRelativeTime() {
        return time - System.currentTimeMillis();
    }

    @Override
    public long getIndex() {
        return index;
    }

    @Override
    public long getRelativeIndex() {
        if (window == 0)
            return INDEX_NA;
        long currentIndex = System.currentTimeMillis() / window;
        return index - currentIndex;
    }

    @Override
    public boolean isHistory() {
        long current = System.currentTimeMillis();
        return time < current;
    }

    @Override
    public boolean isFuture() {
        long current = System.currentTimeMillis();
        return time > current;
    }

    @Override
    public boolean exists() {
        return time != -1L;
    }

    static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
    static {
        timeFormat.setTimeZone(TimeZones.TZ_0);
    }

    @Override
    public String toString() {
        if (time == -1L)
            return "(no index)";

        long relativeIndex = getRelativeIndex();
        long relativeTime = getRelativeTime();
        String ts;
        if (relativeTime < 0)
            ts = "-" + timeFormat.format(-relativeTime);
        else
            ts = "+" + timeFormat.format(relativeTime);
        return String.format("index %+d, time %s", relativeIndex, ts);
    }

    public static FlyingIndex from(JsonObject jo)
            throws ParseException {
        FlyingIndex a = new FlyingIndex();
        a.jsonIn(jo);
        return a;
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        window = o.getLong("window", window);
        time = o.getLong("time", time);
        index = o.getLong("index", index);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entry("window", window);
        out.entry("time", time);
        out.entry("index", index);
        long relativeIndex = getRelativeIndex();
        long relativeTime = getRelativeTime();
        out.entry("relativeIndex", relativeIndex);
        out.entry("relativeTime", relativeTime);
    }

    public FlyingIndex applyOn(AbstractJsonResponse<?> resp) {
        if (resp != null)
            if (exists())
                resp.setHeader("fi", this);
        return this;
    }

}
