package net.bodz.mda.xjdoc.tagtype;

public class DefaultTagType
        extends DecoratedTagType {

    private static final long serialVersionUID = 1L;

    public DefaultTagType() {
        super(StringTagType.getInstance());
    }

    static DefaultTagType instance = new DefaultTagType();

    public static DefaultTagType getInstance() {
        return instance;
    }

}
