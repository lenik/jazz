package net.bodz.bas.disp.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.disp.util.SuffixTokenizer;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.vfs.util.ContentType;

public class RESTfulRequestBuilder {

    public static DefaultRequestDispatch build(HttpServletRequest _request) {

        DefaultRequestDispatch reqdisp = new DefaultRequestDispatch();
        DefaultRequestView reqview = new DefaultRequestView();

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

        // Content-type by browser.
        String acceptContentType = _request.getHeader("Accept-Content-Type");
        if (acceptContentType != null) {
            ContentType contentType = ContentType.getInstance(acceptContentType);
            if (contentType != null)
                reqview.setContentType(contentType);
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
        reqdisp.setDispatchPath(pathStem);

        // Process the rest tokens
        while (baseTokens.hasNext()) {
            String suffix = baseTokens.next();

            char delim = suffix.charAt(0);
            ISuffixDissolver dissolver = suffixDissolvers.get(delim);
            if (dissolver == null)
                throw new IllegalUsageException("Bad suffix: " + suffix);

            String suffixName = suffix.substring(1);
            dissolver.dissolveSuffix(suffixName, reqdisp);
        }

        return reqdisp;
    }

}
