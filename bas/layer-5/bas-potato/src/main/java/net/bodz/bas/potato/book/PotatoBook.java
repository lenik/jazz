package net.bodz.bas.potato.book;

import net.bodz.bas.potato.traits.IElement;
import net.bodz.mda.xjdoc.tags.DocTagType;
import net.bodz.mda.xjdoc.tags.TagBook;
import net.bodz.mda.xjdoc.tags.URLTagType;
import net.bodz.mda.xjdoc.tags.WordsTagType;

/**
 * Potato tag book.
 * 
 * This tag book defines tags commonly used by the facade of applications.
 * 
 * @name The Potato Tag Book
 * @tags tags predefined meta-model
 * @see IElement
 */
public class PotatoBook
        extends TagBook {

    {
        // setTagType("author", DocTagType.INSTANCE.repeat());
        setTagType("name", DocTagType.getInstance());
        setTagType("siteLink", URLTagType.getInstance());
        setTagType("tags", WordsTagType.getInstance());
    }

}
