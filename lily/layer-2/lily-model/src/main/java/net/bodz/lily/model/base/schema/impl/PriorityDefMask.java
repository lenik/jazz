package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.PriorityDef
 */
public class PriorityDefMask
        extends AbstractDefinitionMask {

    public static PriorityDefMask forSchema(int id) {
        PriorityDefMask mask = new PriorityDefMask();
        mask.schemaId = id;
        return mask;
    }

}
