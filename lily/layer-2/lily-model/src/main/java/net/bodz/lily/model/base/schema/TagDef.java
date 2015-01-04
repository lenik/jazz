package net.bodz.lily.model.base.schema;

public class TagDef
        extends AbstractDefinition<TagDef> {

    private static final long serialVersionUID = 1L;

    private TagSetDef tagSet;

    /**
     * Tag Set
     * 
     * @label.zh 标签集
     */
    public TagSetDef getTagSet() {
        return tagSet;
    }

    public void setTagSet(TagSetDef tagSet) {
        this.tagSet = tagSet;
    }

    public void setTagSetId(int tagSetId) {
        (this.tagSet = new TagSetDef()).setId(tagSetId);
    }

    @Override
    public String toString() {
        return getCode() + ":" + getLabel();
    }

}
