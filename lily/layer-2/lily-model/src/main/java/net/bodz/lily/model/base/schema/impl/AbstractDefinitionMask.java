package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
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

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
        schemaId = map.getInt("schema", schemaId);
    }

}
