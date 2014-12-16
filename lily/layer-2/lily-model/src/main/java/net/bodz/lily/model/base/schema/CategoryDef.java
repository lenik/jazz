package net.bodz.lily.model.base.schema;

import net.bodz.bas.meta.cache.Statistics;

public class CategoryDef
        extends AbstractDefinition<CategoryDef> {

    private static final long serialVersionUID = 1L;

    private SchemaDef schema;
    private long refCount;

    public SchemaDef getSchema() {
        return schema;
    }

    public void setSchema(SchemaDef schema) {
        this.schema = schema;
    }

    @Statistics
    public long getRefCount() {
        return refCount;
    }

    public void setRefCount(long refCount) {
        this.refCount = refCount;
    }

}
