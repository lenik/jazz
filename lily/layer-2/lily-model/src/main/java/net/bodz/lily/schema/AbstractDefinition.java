package net.bodz.lily.schema;

import net.bodz.lily.model.base.CoCode;

public abstract class AbstractDefinition<self_t extends AbstractDefinition<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    private SchemaDef schema;

    public AbstractDefinition() {
        super();
    }

    public AbstractDefinition(self_t parent) {
        super(parent);
    }

    /**
     * Schema
     * 
     * @label.zh 模式
     */
    public final SchemaDef getSchema() {
        return schema;
    }

    public final void setSchema(SchemaDef schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        String label = getLabel();
        if (label != null)
            return label;

        String code = getCode();
        if (code != null)
            return code;

        return getId().toString();
    }

}
