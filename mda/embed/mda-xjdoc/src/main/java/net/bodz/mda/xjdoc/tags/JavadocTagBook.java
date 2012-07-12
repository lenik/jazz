package net.bodz.mda.xjdoc.tags;

public class JavadocTagBook
        extends TagBook {

    {
        setTagType("author", DocTagType.getInstance().repeat());
        setTagType("exception", DocTagType.getInstance().typed());
        setTagType("param", DocTagType.getInstance().keyed());
        setTagType("return", DocTagType.getInstance());
        setTagType("throws", DocTagType.getInstance().typed());
    }

}
