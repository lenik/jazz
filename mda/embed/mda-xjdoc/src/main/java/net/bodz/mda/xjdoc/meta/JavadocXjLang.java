package net.bodz.mda.xjdoc.meta;

public class JavadocXjLang
        extends XjLanguage {

    public JavadocXjLang() {
        setTagType("author", StringArrayTagType.INSTANCE);
        setTagType("exception", new KeyedDocTagType()); // checked
        setTagType("param", new KeyedDocTagType());
        setTagType("return", new DocTagType());
        setTagType("throws", new KeyedDocTagType()); // unchecked
    }

    private static JavadocXjLang instance;

    public static synchronized JavadocXjLang getInstance() {
        if (instance == null)
            instance = new JavadocXjLang();
        return instance;
    }

}
