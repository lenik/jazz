package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.ParameterDef
 */
public class ParameterDefMask
        extends AbstractDefinitionMask {

    public static ParameterDefMask forSchema(int id) {
        ParameterDefMask mask = new ParameterDefMask();
        mask.schemaId = id;
        return mask;
    }

}
