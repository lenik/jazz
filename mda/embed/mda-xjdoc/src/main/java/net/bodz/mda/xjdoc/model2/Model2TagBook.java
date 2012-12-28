package net.bodz.mda.xjdoc.model2;

import net.bodz.mda.xjdoc.tags.DocTagType;
import net.bodz.mda.xjdoc.tags.TagBook;
import net.bodz.mda.xjdoc.tags.URLTagType;
import net.bodz.mda.xjdoc.tags.WordsTagType;

/**
 * This tag book defines tags commonly used by the facade of applications.
 * 
 * @name Model-2 Tag Book
 * @name.zh_CN 二级模型标签书
 * @tags tags predefined meta-model
 * @site http://www.example.com
 */
public class Model2TagBook
        extends TagBook {

    {
        // setTagType("author", DocTagType.INSTANCE.repeat());
        setTagType("name", DocTagType.getInstance());
        setTagType("site", URLTagType.getInstance());
        setTagType("tags", WordsTagType.getInstance());
    }

}
