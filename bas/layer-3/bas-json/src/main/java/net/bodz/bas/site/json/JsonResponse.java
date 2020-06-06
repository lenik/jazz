package net.bodz.bas.site.json;

public class JsonResponse
        extends AbstractJsonResponse<JsonResponse> {

    public JsonResponse() {
        super();
    }

    public JsonResponse(int status, String message, Object data) {
        super(status, message, data);
    }

}
