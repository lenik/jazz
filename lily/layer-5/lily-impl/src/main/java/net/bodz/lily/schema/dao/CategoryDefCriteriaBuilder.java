package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.CategoryDef
 */
public class CategoryDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public static CategoryDefCriteriaBuilder forSchema(int id) {
        CategoryDefCriteriaBuilder mask = new CategoryDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
