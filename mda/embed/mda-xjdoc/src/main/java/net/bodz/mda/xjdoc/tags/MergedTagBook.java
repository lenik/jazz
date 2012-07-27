package net.bodz.mda.xjdoc.tags;

import java.util.ArrayList;
import java.util.Arrays;

public class MergedTagBook
        extends ArrayList<ITagBook>
        implements ITagBook {

    private static final long serialVersionUID = 1L;

    public MergedTagBook() {
    }

    public MergedTagBook(TagBook... books) {
        addAll(Arrays.asList(books));
    }

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
