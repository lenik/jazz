package net.bodz.bas.site.json;

import net.bodz.bas.fmt.json.IJsonSerializable;

public class JsonResponse
        extends AbstractJsonResponse<JsonResponse> {

    public JsonResponse() {
        super();
    }

    public JsonResponse(int status, String message, IJsonSerializable data) {
        super(status, message, data);
    }

}
