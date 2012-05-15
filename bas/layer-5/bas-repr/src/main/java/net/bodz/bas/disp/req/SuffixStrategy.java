package net.bodz.bas.disp.req;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.disp.util.SuffixTokenizer;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;

public class SuffixStrategy
        extends HttpRequestProcessor {

    @Override
    public void apply(HttpServletRequest req)
            throws ParseException {
        IRequestDispatch qDisp = RequestUtils.getRequestDispatch(req);

        String path = qDisp.getDispatchPath();

        // int lastSlash = uri.lastIndexOf('/');
        // assert lastSlash != -1;
        // String dirName = uri.substring(0, lastSlash);
        // String baseName = uri.substring(lastSlash + 1);

        String baseName = StringPart.afterLast(path, '/');

        SuffixTokenizer tokens = new SuffixTokenizer(baseName, true/* include-first */);

        // Remove the first token as the base stem
        String baseStem = tokens.next();

// String pathStem = dirName;
// if (!baseStem.isEmpty())
// pathStem += "/" + baseStem;
// qDisp.setDispatchPath(pathStem);

        // Process the rest tokens
        while (tokens.hasNext()) {
            String suffix = tokens.next();

            char delim = suffix.charAt(0);
            ISuffixDissolver dissolver = suffixDissolvers.get(delim);
            if (dissolver == null)
                throw new IllegalUsageException("Bad suffix: " + suffix);

            String suffixName = suffix.substring(1);
            dissolver.dissolveSuffix(suffixName, qDisp);
        }
    }

}
