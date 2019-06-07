package net.bodz.lily.schema.impl;

/**
 * @see net.bodz.lily.schema.PhaseDef
 */
public class PhaseDefMask
        extends AbstractDefinitionMask {

    public static PhaseDefMask forSchema(int id) {
        PhaseDefMask mask = new PhaseDefMask();
        mask.schemaId = id;
        return mask;
    }

}
