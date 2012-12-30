package net.bodz.mda.xjdoc.model.artifact;

import net.bodz.mda.xjdoc.taglib.AbstractTagLibrary;
import net.bodz.mda.xjdoc.tagtype.TagSpecKeyTagType;
import net.bodz.mda.xjdoc.tagtype.TokensTagType;
import net.bodz.mda.xjdoc.tagtype.URLTagType;

/**
 * This tag library defines tags commonly used by the facade of applications.
 * 
 * @label Model-2 Tag Book
 * @label.zh.cn 二级模型标签书
 * @tags tags, predefined, meta-model
 * @site http://www.example.com
 */
public class ArtifactTagLibrary
        extends AbstractTagLibrary {

    public ArtifactTagLibrary() {
        addTagType("lang", TokensTagType.COMMA_SEPARATED);

        addTagType("site", new TagSpecKeyTagType(//
                URLTagType.getInstance()));

        addTagType("tags", TokensTagType.COMMA_SEPARATED);
    }

}
