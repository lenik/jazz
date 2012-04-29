package net.bodz.bas.repr.request;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.repr.MethodNames;
import net.bodz.bas.repr.rest.RESTfulRequest;
import net.bodz.bas.vfs.util.MIMEType;

public class RESTfulRequestBuilder {

    private static Map<Character, ISuffixDissolver> suffixDissolvers;

    private static MethodSuffixDissolver methodSuffixDissolver = MethodSuffixDissolver.INSTANCE;
    private static ContentTypeDissolver contentTypeDissolver = ContentTypeDissolver.INSTANCE;

    static {
        suffixDissolvers = new HashMap<Character, ISuffixDissolver>();
        suffixDissolvers.put('*', methodSuffixDissolver);
        suffixDissolvers.put('.', contentTypeDissolver);
    }

    static TreeSet<IRequestPreprocessor> preprocessors;

    static void reloadServices() {
        ServiceLoader<IRequestPreprocessor> preprocessorServices = ServiceLoader.load(IRequestPreprocessor.class);

        preprocessors = new TreeSet<IRequestPreprocessor>(RequestPreprocessorComparator.getInstance());

        for (IRequestPreprocessor preprocessor : preprocessorServices) {
            preprocessors.add(preprocessor);
        }
        if (preprocessors.isEmpty())
            preprocessors = null;
    }

    static {
        reloadServices();
    }

    public static RESTfulRequest build(HttpServletRequest _request) {

        RESTfulRequest request = new RESTfulRequest(_request);

        // Prefix-mapping?
        String path = _request.getPathInfo();
        if (path == null) {
            // Not prefix mapping, use the entire URL.
            path = _request.getRequestURI();

            String servletPath = _request.getServletPath();
            if (servletPath != null && servletPath.equals(path))
                // Prefix-mapping, but chopped the ending '/'.
                // Not a restful-request.
                return null;
        }

        // Translate http method
        String method = _request.getParameter(".method");
        if (method == null) {
            method = _request.getParameter("method");
            if (method == null) {
                method = _request.getParameter("X");
                if (method == null) {
                    if (path.endsWith("/"))
                        method = MethodNames.INDEX;
                    else
                        method = HttpMethodNames.getMethodName(_request.getMethod());
                }
            }
        }
        request.setMethod(method);

        // Content-type by browser.
        String acceptContentType = _request.getHeader("Accept-Content-Type");
        if (acceptContentType != null) {
            MIMEType contentType = MIMEType.getInstance(acceptContentType);
            if (contentType != null)
                request.setTargetContentType(contentType);
        }

        int lastSlash = path.lastIndexOf('/');
        assert lastSlash != -1;
        String dirName = path.substring(0, lastSlash);
        String baseName = path.substring(lastSlash + 1);

        SuffixTokenizer baseTokens = new SuffixTokenizer(baseName, true);

        // Remove the first token as the base stem
        String baseStem = baseTokens.next();

        String pathStem = dirName;
        if (!baseStem.isEmpty())
            pathStem += "/" + baseStem;
        request.setDispatchPath(pathStem);

        // Process the rest tokens
        while (baseTokens.hasNext()) {
            String suffix = baseTokens.next();

            char delim = suffix.charAt(0);
            ISuffixDissolver dissolver = suffixDissolvers.get(delim);
            if (dissolver == null)
                throw new IllegalUsageException("Bad suffix: " + suffix);

            String suffixName = suffix.substring(1);
            dissolver.dissolveSuffix(suffixName, request);
        }

        // other preprocessors.
        if (preprocessors != null) {
            for (IRequestPreprocessor preprocessor : preprocessors)
                preprocessor.preprocess(request);
        }

        return request;
    }

}
