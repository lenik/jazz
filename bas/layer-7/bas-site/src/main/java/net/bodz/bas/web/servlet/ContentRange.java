package net.bodz.bas.web.servlet;

import java.io.Serializable;

public class ContentRange
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int BYTE = 0;

    public int unit = BYTE;
    public long start;
    public long end;

    public long instanceLength;
    public boolean unlimited;

    /**
     * @param str
     *            Non-<code>null</code> Content-Range field to be parsed.
     * @return Error message. <code>null</code> if succeeded.
     */
    public String parse(String str) {
        if (str == null)
            throw new NullPointerException("str");

        str = str.trim();
        int SP = str.indexOf(' ');
        if (SP == -1)
            return "no space";

        int unit = BYTE;
        String unitSpec = str.substring(0, SP).trim();
        switch (unitSpec) {
        case "bytes":
            unit = BYTE;
            break;
        default:
            return "bad unit: " + unitSpec;
        }

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
            long start = Long.parseLong(first);
            long end = Long.parseLong(last) + 1;
            boolean unlimited = "*".equals(lengthSpec);
            long length = unlimited ? 0 : Long.parseLong(lengthSpec);

            this.unit = unit;
            this.start = start;
            this.end = end;
            this.instanceLength = length;
            this.unlimited = unlimited;
            return null;
        } catch (NumberFormatException e) {
            return "illegal number format.";
        }
    }

}
