package net.bodz.lily.schema.impl;

import net.bodz.lily.model.base.CoNodeMask;

public class AbstractDefinitionMask
        extends CoNodeMask {

    public Integer schemaId;

    public Integer getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Integer schemaId) {
        this.schemaId = schemaId;
    }

}
