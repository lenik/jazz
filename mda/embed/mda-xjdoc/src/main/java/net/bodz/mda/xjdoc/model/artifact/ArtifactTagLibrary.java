package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;
import net.bodz.mda.xjdoc.tagtype.URLTagType;
import net.bodz.mda.xjdoc.tagtype.WordsTagType;

/**
 * This tag library defines tags commonly used by the facade of applications.
 * 
 * @name Model-2 Tag Book
 * @name.zh_CN 二级模型标签书
 * @tags tags predefined meta-model
 * @site http://www.example.com
 */
public class ArtifactTagLibrary
        extends TagLibrary {

    {
        // setTagType("author", DocTagType.INSTANCE.repeat());
        addTagType("name", I18nStringTagType.getInstance());
        addTagType("site", URLTagType.getInstance());
        addTagType("tags", WordsTagType.getInstance());
    }

}
