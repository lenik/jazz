package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.AttributeDef
 */
public class AttributeDefCriteria
        extends AbstractDefinitionCriteria {

    public static AttributeDefCriteria forSchema(int id) {
        AttributeDefCriteria criteria = new AttributeDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
