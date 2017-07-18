package net.bodz.bas.i18n.dom;

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

        public static boolean isEmpty(iString s) {
            if (s == null)
                return true;
            String str = s.toString();
            if (str == null)
                return true;
            if (str.trim().isEmpty())
                return true;
            return false;
        }

    }

}
