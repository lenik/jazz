package net.bodz.bas.t.variant;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class VariantMaps {

    public static IVariantMap<String> fromRequestOpt(HttpServletRequest request) {
        if (request == null)
            return new NullVariantMap<String>();
        else
            return fromRequest(request);
    }

    public static IVariantMap<String> fromRequest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        return fromParameterMap(map);
    }

    public static IVariantMap<String> fromParameterMap(Map<String, String[]> map) {
        return new ParameterMapVariantMap(map);
    }

}
