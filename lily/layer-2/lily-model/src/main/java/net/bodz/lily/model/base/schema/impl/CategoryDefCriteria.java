package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.CategoryDef
 */
public class CategoryDefCriteria
        extends AbstractDefinitionCriteria {

    public static CategoryDefCriteria forSchema(int id) {
        CategoryDefCriteria criteria = new CategoryDefCriteria();
        criteria.setSchemaId(id);
        return criteria;
    }

}
