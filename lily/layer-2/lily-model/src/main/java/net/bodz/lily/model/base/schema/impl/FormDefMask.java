package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.FormDef
 */
public class FormDefMask
        extends AbstractDefinitionMask {

    public static FormDefMask forSchema(int id) {
        FormDefMask mask = new FormDefMask();
        mask.schemaId = id;
        return mask;
    }

}
