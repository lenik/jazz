package net.bodz.bas.disp.req;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.variant.map.IVariantLookupMap;
import net.bodz.bas.variant.map.Map2VariantMap;

/**
 * Find method name in the dispatch path, translate into request attributes and remove them from
 * path.
 * 
 * This preprocessor find method name in following order, [F] means "final", if there are multiple
 * non-final method name occurrences, the latter ones will overwrite the former ones.
 * 
 * <ol>
 * <li>HTTP request parameter: method:
 * <li>HTTP request parameter: m:
 * <li>HTTP-DELETE, PUT, POST, ... (other then GET)
 * <li>"read"
 * </ol>
 */
public class DefaultRequestMethod
        extends HttpRequestProcessor
        implements IRequestMethod {

    String methodName;
    Map2VariantMap<String> parameters;

    DefaultRequestMethod() {
    }

    public DefaultRequestMethod(String methodName, Map<String, Object> parameterMap) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        if (parameterMap == null)
            throw new NullPointerException("parameterMap");
        this.methodName = methodName;
        this.parameters = new Map2VariantMap<String>(parameterMap);
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public void setMethodName(String methodName) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        this.methodName = methodName;
    }

    @Override
    public IVariantLookupMap<String> getParameters() {
        return parameters;
    }

    static boolean httpMethods = true;
    static boolean paramMethodLong = true;
    static boolean paramMethod = true;

    @Override
    public void apply(HttpServletRequest request)
            throws ParseException {
        String methodName = null;

        if (methodName == null && paramMethodLong)
            methodName = HttpRequestProcessor.getParameter(request, "method:", true);

        if (methodName == null && httpMethods) {
            String httpMethod = HttpMethods.getMethodName(request.getMethod());
            if (!MethodNames.READ.equals(httpMethod))
                methodName = httpMethod;
        }

        if (methodName == null && paramMethod)
            methodName = HttpRequestProcessor.getParameter(request, "m:", true);

        Map<String, Object> mparams = new HashMap<String, Object>();
        for (Entry<String, ?> entry : ((Map<String, ?>) request.getParameterMap()).entrySet()) {
            String param = entry.getKey();
            if (param.startsWith("m:"))
                mparams.put(param.substring(2), entry.getValue());
        }

        this.methodName = methodName;
        this.parameters.setWrapped(mparams);

        request.setAttribute(ATTRIBUTE_KEY, this);
    }

    public static DefaultRequestMethod parse(HttpServletRequest request)
            throws ParseException {
        DefaultRequestMethod m = new DefaultRequestMethod();
        m.apply(request);
        return m;
    }

}
