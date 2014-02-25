package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;

/**
 * I18n-capable String
 */
public interface iString
        extends IDomainMap<String>, Cloneable {

    iString NULL = NulliString.INSTANCE;

    iString clone();

    String toString();

    /**
     * Remove html tags from toString.
     */
    String toPlainText();

    String toParaLangString();

    String toParaLangString(String separator);

    String toMultiLangString();

    String toMultiLangString(String langSeparator, String lineSeparator);

    String dumpContent();

    iString append(iString other);

    iString concat(iString other);

    /**
     * For domain strings A(default1,en1) and B(default3,et4), the result of join will be
     * C(default13,en13,et14).
     */
    iString join(iString other);

    iString headPar();

    iString tailPar();

    String getHeadPar();

    String getTailPar();

    class fn {

        public static iString val(Object obj) {
            if (obj == null)
                return NULL;
            if (obj instanceof iString)
                return (iString) obj;
            else
                return val(obj.toString());
        }

        public static iString val(String plainString) {
            return new XiString(plainString);
        }

        /**
         * A para-lang string is formatted as:
         *
         * <pre>
         * string for default-locale
         *
         * &lt;p lang="LOCALE1"&gt;
         *      string for locale1...
         *
         * &lt;p lang="LOCALE2"&gt;
         *      string for locale2...
         * </pre>
         *
         * @see ParaLangString#parse(iString, String)
         */
        public static iString parseParaLangString(String plText) {
            XiString ds = new XiString();
            ParaLangString.parse(ds, plText);
            return ds;
        }

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
        public static iString parseMultiLangString(String mlstr)
                throws ParseException {
            if (mlstr == null)
                return null;
            MultiLangStringParser parser = new MultiLangStringParser();
            return parser.parse(mlstr);
        }

        public static String toParaLangString(iString s, String separator) {
            return ParaLangString.format(s, separator);
        }

        public static String toMultiLangString(iString s, String langSeparator, String lineSeparator) {
            MultiLangStringFormatter formatter = new MultiLangStringFormatter();
            formatter.setDomainSeparator(langSeparator);
            formatter.setLineSeparator(lineSeparator);
            return formatter.format(s);
        }

    }

}
