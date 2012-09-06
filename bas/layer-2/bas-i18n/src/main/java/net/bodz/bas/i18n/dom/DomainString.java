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

    DomainString append(DomainString other);

    DomainString concat(DomainString other);

    DomainString join(DomainString other);

    String dumpContent();

}
