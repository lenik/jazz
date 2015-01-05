package net.bodz.bas.repr.req;

import java.util.HashMap;
import java.util.Map;

class HttpMethods {

    static final Map<String, String> map;

    static {
        map = new HashMap<String, String>();
        map.put("get", MethodNames.READ);
        map.put("put", MethodNames.CREATE);
        map.put("post", MethodNames.UPDATE);
        map.put("delete", MethodNames.DELETE);
        map.put("head", MethodNames.ESTATE);
    }

    public static String getMethodName(String httpMethod) {
        if (httpMethod == null)
            throw new NullPointerException("httpMethod");

        httpMethod = httpMethod.toLowerCase();

        String name = map.get(httpMethod);
        if (name != null)
            return name;
        else
            return httpMethod;
    }

}
