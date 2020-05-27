package net.bodz.bas.std.rfc.http;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

public class ContentRange
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int BYTE = 0;

    public int unit = BYTE;
    public Long start;
    public Long end;
    public Long lengthTotal;

    @Override
    public String toString() {
        return "[ " + start + ", " + end + " ) / " + lengthTotal;
    }

    /**
     * @param str
     *            Non-<code>null</code> Range field to be parsed.
     * @param posBase
     *            0-based or 1-based.
     * @return Error message. <code>null</code> if succeeded.
     */
    public String parseRange(String str, int posBase) {
        if (str == null)
            throw new NullPointerException("str");

        str = str.trim();
        int EQ = str.indexOf('=');
        if (EQ == -1)
            return "no eq('=')";

        int unit = BYTE;
        String unitSpec = str.substring(0, EQ).trim();
        if ("bytes".equals(unitSpec))
            unit = BYTE;
        else
            return "bad unit: " + unitSpec;

        str = str.substring(EQ + 1);
        int dash = str.indexOf('-');
        if (dash == -1)
            return "no dash";

        String first = str.substring(0, dash).trim();
        String last = str.substring(dash + 1).trim();
        try {
            this.unit = unit;
            this.start = first.isEmpty() ? null : Long.parseLong(first) - posBase;
            this.end = last.isEmpty() ? null : Long.parseLong(last) - posBase + 1;
            return null;
        } catch (NumberFormatException e) {
            return "illegal number format.";
        }
    }

    /**
     * Format: <code>Content-Range: bytes FIRST-LAST/TOTAL</code>.
     * <p>
     * Examples:
     * <ul>
     * <li><code>bytes 21010-47021/47022</code>
     * </ul>
     *
     * @param str
     *            Non-<code>null</code> Content-Range field to be parsed.
     * @return Error message. <code>null</code> if succeeded.
     */
    public String parseContentRange(String str) {
        if (str == null)
            throw new NullPointerException("str");

        str = str.trim();
        int SP = str.indexOf(' ');
        if (SP == -1)
            return "no space";

        int unit = BYTE;
        String unitSpec = str.substring(0, SP).trim();
        if ("bytes".equals(unitSpec))
            unit = BYTE;
        else
            return "bad unit: " + unitSpec;

        str = str.substring(SP + 1);
        int slash = str.indexOf('/');
        if (slash == -1)
            return "no slash";

        String respSpec = str.substring(0, slash).trim();
        String lengthSpec = str.substring(slash + 1).trim();

        int dash = respSpec.indexOf('-');
        if (dash == -1)
            return "no dash";

        String first = respSpec.substring(0, dash);
        String last = respSpec.substring(dash + 1);
        try {
            this.unit = unit;
            this.start = Long.parseLong(first);
            this.end = Long.parseLong(last) + 1;

            boolean lengthUnknown = "*".equals(lengthSpec);
            this.lengthTotal = lengthUnknown ? null : Long.parseLong(lengthSpec);
            return null;
        } catch (NumberFormatException e) {
            return "illegal number format.";
        }
    }

    public String toContentRange() {
        return toContentRange(0);
    }

    public String toContentRange(int posBase) {
        long first = start + posBase;
        long last = end - 1 + posBase;
        return "bytes " + first + "-" + last + "/" + lengthTotal;
    }

    public static ContentRange parseAndSet(long lengthAvailable, String rangeHeader, HttpServletResponse resp)
            throws IOException {
        ContentRange range = new ContentRange();

        String error = range.parseRange(rangeHeader, 0); // zero-base
        if (error != null) {
            resp.setHeader("Content-Range", "*/" + lengthAvailable);
            resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, //
                    error + ": " + rangeHeader);
            return null;
        }

        if (range.end == null)
            range.end = lengthAvailable;

        if (range.start == null) {
            range.start = lengthAvailable - range.end;
            range.end = lengthAvailable;
        }

        if (range.end > lengthAvailable)
            range.end = lengthAvailable;

        range.lengthTotal = lengthAvailable;

        resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        resp.setHeader("Content-Range", range.toContentRange());
        return range;
    }

}
