package net.bodz.bas.site.json;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fn.IExecutableX;

public class JsonResult
        extends AbstractJsonResponse<JsonResult> {

    public JsonResult() {
        super();
    }

    public JsonResult(int status, String message, IJsonForm data) {
        super(status, message, data);
    }

    public void execute(IExecutableX<Throwable> executable) {
        try {
            executable.execute();
            succeed();
        } catch (Throwable e) {
            fail(e, e.getMessage());
        }
    }

    public static JsonResult exec(IExecutableX<Throwable> executable) {
        JsonResult result = new JsonResult();
        try {
            executable.execute();
            result.succeed();
        } catch (Throwable e) {
            result.fail(e, e.getMessage());
        }
        return result;
    }

    private static ThreadLocal<List<JsonLogger>> _tls = new ThreadLocal<>();

    static synchronized List<JsonLogger> getThreadLocalList() {
        List<JsonLogger> list = _tls.get();
        if (list == null)
            _tls.set(list = new ArrayList<>());
        return list;
    }

    public static void addThreadJsonLogger(JsonLogger logger) {
        getThreadLocalList().add(logger);
    }

    public static void removeThreadJsonLogger(JsonLogger logger) {
        if (logger != null)
            getThreadLocalList().remove(logger);
    }

}
