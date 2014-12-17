package net.bodz.lily.model.base.schema;

import net.bodz.bas.meta.cache.Statistics;

import net.bodz.lily.model.base.CoCode;

public abstract class AbstractDefinition<self_t extends AbstractDefinition<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    private SchemaDef schema;
    private long refCount;

    public AbstractDefinition() {
        super();
    }

    public AbstractDefinition(self_t parent) {
        super(parent);
    }

    /**
     * Schema
     * 
     * @label.zh.cn 模式
     */
    public final SchemaDef getSchema() {
        return schema;
    }

    public final void setSchema(SchemaDef schema) {
        this.schema = schema;
    }

    /**
     * Reference Count
     * 
     * @label.zh.cn 引用统计
     */
    @Statistics
    public final long getRefCount() {
        return refCount;
    }

    public void setRefCount(long refCount) {
        this.refCount = refCount;
    }

}
