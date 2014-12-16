package net.bodz.lily.model.base.schema;

import net.bodz.bas.meta.cache.Statistics;

public class TagDef
        extends AbstractDefinition<TagDef> {

    private static final long serialVersionUID = 1L;

    private TagSetDef tagSet;
    private long refCount;

    public TagSetDef getTagSet() {
        return tagSet;
    }

    public void setTagSet(TagSetDef tagSet) {
        this.tagSet = tagSet;
    }

    public void setTagSetId(int tagSetId) {
        (this.tagSet = new TagSetDef()).setId(tagSetId);
    }

    @Statistics
    public long getRefCount() {
        return refCount;
    }

    public void setRefCount(long refCount) {
        this.refCount = refCount;
    }

}
