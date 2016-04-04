package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.PhaseDef
 */
public class PhaseDefMask
        extends AbstractDefinitionMask {

    public static PhaseDefMask forSchema(int id) {
        PhaseDefMask mask = new PhaseDefMask();
        mask.schemaId = id;
        return mask;
    }

}
