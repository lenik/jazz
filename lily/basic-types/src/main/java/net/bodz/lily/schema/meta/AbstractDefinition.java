package net.bodz.lily.schema.meta;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCode;

@TypeParameters({ TypeParamType.THIS_REC })
public abstract class AbstractDefinition<self_t extends AbstractDefinition<self_t>>
        extends CoCode<self_t> {

    private static final long serialVersionUID = 1L;

    private SchemaDef schema;
    private int schemaId;

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

    @Precision(value = 19)
    @Column(name = "schema")
    public synchronized int getSchemaId() {
        if (schema != null) {
            return schema.getId();
        }
        return schemaId;
    }

    public synchronized void setSchemaId(int value) {
        this.schemaId = value;
    }

    @Override
    public String toString() {
        String label = getLabel();
        if (label != null)
            return label;

        String code = getCode();
        if (code != null)
            return code;

        Integer id = id();
        return id == null ? null : String.valueOf(id);
    }

}
