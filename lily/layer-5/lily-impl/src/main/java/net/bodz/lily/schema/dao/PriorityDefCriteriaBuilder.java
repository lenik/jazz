package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.PriorityDef
 */
public class PriorityDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public static PriorityDefCriteriaBuilder forSchema(int id) {
        PriorityDefCriteriaBuilder mask = new PriorityDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
