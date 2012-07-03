package net.bodz.mda.xjdoc.meta;

import java.util.ArrayList;

public class MergedTagBook
        extends ArrayList<ITagBook>
        implements ITagBook {

    private static final long serialVersionUID = 1L;

    @Override
    public ITagType getTagType(String tagName) {
        for (ITagBook book : this) {
            ITagType tagType = book.getTagType(tagName);
            if (tagType != null)
                return tagType;
        }
        return null;
    }

}
