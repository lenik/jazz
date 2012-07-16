package net.bodz.bas.i18n.dom;

public interface DomainString
        extends IDomainMap<String>, Cloneable {

    DomainString clone();

    String getDomain();

    String getValue();

    void setValue(String value);

    String toParaLangString();

    String toParaLangString(String separator);

    String toMultiLangString();

    String toMultiLangString(String langSeparator, String lineSeparator);

    DomainString concat(DomainString other);

    DomainString join(DomainString other);

    String dumpContent();

}
