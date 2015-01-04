package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.FormDef
 */
public class FormDefCriteria
        extends AbstractDefinitionCriteria {

    public static FormDefCriteria forSchema(int id) {
        FormDefCriteria criteria = new FormDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
