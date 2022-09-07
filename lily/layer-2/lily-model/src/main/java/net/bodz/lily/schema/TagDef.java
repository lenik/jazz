package net.bodz.lily.schema;

import javax.persistence.Table;

/**
 * @label Tag
 * @label.zh.cn 标签
 */
@Table(name = "tag")
public class TagDef
        extends AbstractDefinition<TagDef> {

    private static final long serialVersionUID = 1L;

    private TagGroupDef tagGroup;

    /**
     * Tag Group
     *
     * @label.zh 标签组
     */
    public TagGroupDef getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroupDef tagGroup) {
        this.tagGroup = tagGroup;
    }

    public void setTagGroupId(int tagGroupId) {
        (this.tagGroup = new TagGroupDef()).id(tagGroupId);
    }

}
