package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.ParameterDef
 */
public class ParameterDefCriteria
        extends AbstractDefinitionCriteria {

    public static ParameterDefCriteria forSchema(int id) {
        ParameterDefCriteria criteria = new ParameterDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
