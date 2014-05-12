package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;

public class MultiLangStrings {

    /**
     * A multi-lang string is formatted as:
     *
     * <pre>
     * "default-locale"
     * LOCALE1 "string for locale1"
     *         "more..."
     * LOCALE2 "string for locale2"
     *         "more..."
     * </pre>
     *
     * @param mlstr
     *            multi-lang string to be parsed.
     * @return <code>null</code> iif <code>mlstr</code> is <code>null</code>.
     * @throws ParseException
     */
    public static iString parse(String mlstr)
            throws ParseException {
        if (mlstr == null)
            return null;
        MultiLangStringParser parser = new MultiLangStringParser();
        return parser.parse(mlstr);
    }

    public static String format(iString s, String langSeparator, String lineSeparator) {
        MultiLangStringFormatter formatter = new MultiLangStringFormatter();
        formatter.setDomainSeparator(langSeparator);
        formatter.setLineSeparator(lineSeparator);
        return formatter.format(s);
    }

}
