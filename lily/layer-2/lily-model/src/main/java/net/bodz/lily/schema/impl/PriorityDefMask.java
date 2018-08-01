package net.bodz.lily.schema.impl;

/**
 * @see net.bodz.lily.schema.PriorityDef
 */
public class PriorityDefMask
        extends AbstractDefinitionMask {

    public static PriorityDefMask forSchema(int id) {
        PriorityDefMask mask = new PriorityDefMask();
        mask.schemaId = id;
        return mask;
    }

}
