package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.PhaseDef
 */
public class PhaseDefCriteria
        extends AbstractDefinitionCriteria {

    public static PhaseDefCriteria forSchema(int id) {
        PhaseDefCriteria criteria = new PhaseDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
