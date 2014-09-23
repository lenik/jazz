package net.bodz.lily.model.base.schema;

public class CategoryDef
        extends AbstractDefinition<CategoryDef> {

    private static final long serialVersionUID = 1L;

    private SchemaDef schema;

    public SchemaDef getSchema() {
        return schema;
    }

    public void setSchema(SchemaDef schema) {
        this.schema = schema;
    }

}
