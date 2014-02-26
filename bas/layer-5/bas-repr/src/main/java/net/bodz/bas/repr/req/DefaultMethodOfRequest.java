package net.bodz.bas.repr.req;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.t.variant.IVariantLookupMap;
import net.bodz.bas.t.variant.VariantMap;

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
public class DefaultMethodOfRequest
        extends AbstractHttpRequestProcessor
        implements IMethodOfRequest, Serializable {

    private static final long serialVersionUID = 1L;

    String methodName;
    VariantMap<String> parameters;

    public DefaultMethodOfRequest() {
        this.parameters = new VariantMap<>();
    }

    public DefaultMethodOfRequest(String methodName, Map<String, Object> parameterMap) {
        if (methodName == null)
            throw new NullPointerException("methodName");
        if (parameterMap == null)
            throw new NullPointerException("parameterMap");
        this.methodName = methodName;
        this.parameters = new VariantMap<String>(parameterMap);
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
    public void apply(HttpServletRequest request) {
        String methodName = null;

        if (methodName == null && paramMethodLong)
            methodName = request.getParameter("method:");

        if (methodName == null && paramMethod)
            methodName = request.getParameter("m:");

        if (methodName == null && httpMethods) {
            String httpMethod = HttpMethods.getMethodName(request.getMethod());
            if (!MethodNames.READ.equals(httpMethod))
                methodName = httpMethod;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        for (Entry<String, ?> entry : ((Map<String, ?>) request.getParameterMap()).entrySet()) {
            String param = entry.getKey();
            if (param.startsWith("m:") && param.length() > 2)
                params.put(param.substring(2), entry.getValue());
        }

        this.methodName = methodName;
        this.parameters.setWrapped(params);

        request.setAttribute(ATTRIBUTE_KEY, this);
    }

}
