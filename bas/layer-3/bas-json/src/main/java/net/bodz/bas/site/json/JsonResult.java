package net.bodz.bas.site.json;

import net.bodz.bas.fmt.json.IJsonSerializable;

public class JsonResult
        extends AbstractJsonResponse<JsonResult> {

    public JsonResult() {
        super();
    }

    public JsonResult(int status, String message, IJsonSerializable data) {
        super(status, message, data);
    }

}
