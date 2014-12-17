package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.schema.TagDef
 */
public class TagDefCriteria
        extends AbstractDefinitionCriteria {

    Integer schemaId;

    public Integer getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Integer schemaId) {
        this.schemaId = schemaId;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        schemaId = map.getInt("schema", schemaId);
    }

}
