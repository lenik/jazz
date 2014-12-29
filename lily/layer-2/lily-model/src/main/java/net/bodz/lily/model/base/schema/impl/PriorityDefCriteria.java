package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.PriorityDef
 */
public class PriorityDefCriteria
        extends AbstractDefinitionCriteria {

    public static PriorityDefCriteria forSchema(int id) {
        PriorityDefCriteria criteria = new PriorityDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
