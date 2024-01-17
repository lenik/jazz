package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.PhaseDef
 */
public class PhaseDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public static PhaseDefCriteriaBuilder forSchema(int id) {
        PhaseDefCriteriaBuilder mask = new PhaseDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
