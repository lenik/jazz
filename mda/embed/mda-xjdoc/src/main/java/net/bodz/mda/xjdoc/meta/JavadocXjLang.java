package net.bodz.mda.xjdoc.meta;

public class JavadocXjLang
        extends XjLanguage {

    public JavadocXjLang() {
        setTagType("return", new DocTagType());
        setTagType("param", new KeyedDocTagType());
        setTagType("exception", new KeyedDocTagType());
    }

    static JavadocXjLang instance;

    public static synchronized JavadocXjLang getInstance() {
        if (instance == null)
            instance = new JavadocXjLang();
        return instance;
    }

}
