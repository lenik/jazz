package net.bodz.bas.i18n.dom;

public interface DomainString
        extends IDomainMap<String>, Cloneable {

    DomainString clone();

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

    DomainString append(DomainString other);

    DomainString concat(DomainString other);

    /**
     * For domain strings A(default1,en1) and B(default3,et4), the result of join will be
     * C(default13,en13,et14).
     */
    DomainString join(DomainString other);

    DomainString headPar();

    DomainString tailPar();

    String getHeadPar();

    String getTailPar();

}
