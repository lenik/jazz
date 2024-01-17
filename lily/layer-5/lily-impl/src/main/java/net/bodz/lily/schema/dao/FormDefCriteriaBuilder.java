package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.FormDef
 */
public class FormDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public static FormDefCriteriaBuilder forSchema(int id) {
        FormDefCriteriaBuilder mask = new FormDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
