package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.ParameterDef
 */
public class ParameterDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public static ParameterDefCriteriaBuilder forSchema(int id) {
        ParameterDefCriteriaBuilder mask = new ParameterDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
