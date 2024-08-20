package net.bodz.bas.site.json;

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

}
