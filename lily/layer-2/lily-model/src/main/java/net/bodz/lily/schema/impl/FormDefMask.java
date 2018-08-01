package net.bodz.lily.schema.impl;

/**
 * @see net.bodz.lily.schema.FormDef
 */
public class FormDefMask
        extends AbstractDefinitionMask {

    public static FormDefMask forSchema(int id) {
        FormDefMask mask = new FormDefMask();
        mask.schemaId = id;
        return mask;
    }

}
