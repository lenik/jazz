package net.bodz.lily.model.base.schema;

public class TagDef
        extends AbstractDefinition<TagDef> {

    private static final long serialVersionUID = 1L;

    private TagSetDef tagSet;

    public TagSetDef getTagSet() {
        return tagSet;
    }

    public void setTagSet(TagSetDef tagSet) {
        this.tagSet = tagSet;
    }

    public void setTagSetId(int tagSetId) {
        (this.tagSet = new TagSetDef()).setId(tagSetId);
    }

}
