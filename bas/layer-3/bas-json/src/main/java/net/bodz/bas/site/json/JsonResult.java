package net.bodz.bas.site.json;

import net.bodz.bas.fmt.json.IJsonForm;

public class JsonResult
        extends AbstractJsonResponse<JsonResult> {

    public JsonResult() {
        super();
    }

    public JsonResult(int status, String message, IJsonForm data) {
        super(status, message, data);
    }

}
