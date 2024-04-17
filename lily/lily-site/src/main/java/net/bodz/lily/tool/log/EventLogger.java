package net.bodz.lily.tool.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.json.HttpPayload;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.security.IUser;

public class EventLogger
        implements
            IPathDispatchable {

    File logDir;
    LinkedList<EventLog> logs;
    EventLog pend;

    public EventLogger() {
        logs = new LinkedList<>();

        String home = SystemProperties.getUserHome();
        File dataDir = new File(home, "data");
        logDir = new File(dataDir, "log");
    }

    public LinkedList<EventLog> getLogs() {
        return logs;
    }

    public EventLog log(IUser user)
            throws IOException {
        flush();
        EventLog item = new EventLog(user);
        this.pend = item;
        return item;
    }

    public EventLog log(IUser user, CoEntity<? extends Number> entity, String message)
            throws IOException {
        flush();
        EventLog item = new EventLog(user, entity, message, null);
        logs.add(item);
        this.pend = item;
        return item;
    }

    public synchronized void flush()
            throws IOException {
        if (pend != null) {
            save(pend.getTime(), Arrays.asList(pend));
            pend = null;
        }
    }

    void save(long time, List<EventLog> logs)
            throws IOException {
        String yearMonth = DateTimes.YYYY_MM.format(Instant.ofEpochMilli(time));
        File subDir = new File(logDir, yearMonth);
        subDir.mkdirs();

        String date = DateTimes.ISO_LOCAL_DATE.format(Instant.ofEpochMilli(time));
        File logFile = new File(subDir, date);
        FileOutputStream fout = new FileOutputStream(logFile, true);
        PrintStream out = new PrintStream(fout, true, "utf-8");

        for (EventLog log : logs)
            out.println(log.toString());

        out.close();
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object target = null;

        String token = tokens.peek();
        if (token == null)
            return null;

        switch (token) {
        case "add":
            JsonResult result = new JsonResult();
            JsonObject jsonObj = HttpPayload.getJsonPayload(CurrentHttpService.getRequest());
            try {
                EventLog log = new EventLog();
                log.jsonIn(jsonObj, JsonFormOptions.DEFAULT);
                log.setTime(System.currentTimeMillis());
                logs.add(log);
            } catch (ParseException e) {
                throw new PathDispatchException("Parse error:" + e.getMessage(), e);
            }
            result.succeed();
            target = result;
            break;

        case "data.json":
            JsonBuffer jsbuf = new JsonBuffer();
            jsbuf.array();
            Iterator<EventLog> iter = logs.descendingIterator();
            while (iter.hasNext()) {
                EventLog log = iter.next();
                try {
                    log.jsonOutValue(jsbuf, JsonFormOptions.DEFAULT);
                } catch (Exception e) {
                    throw new UnexpectedException(e.getMessage(), e);
                }
            }
            jsbuf.endArray();
            target = jsbuf;
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

    private static EventLogger instance = new EventLogger();

    public static EventLogger getInstance() {
        return instance;
    }

}
