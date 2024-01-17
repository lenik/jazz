package net.bodz.lily.schema.dao;

import net.bodz.lily.model.base.CoNodeCriteriaBuilder;

public class AbstractDefinitionCriteriaBuilder
        extends CoNodeCriteriaBuilder {

    public Integer schemaId;

    public Integer getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Integer schemaId) {
        this.schemaId = schemaId;
    }

}
