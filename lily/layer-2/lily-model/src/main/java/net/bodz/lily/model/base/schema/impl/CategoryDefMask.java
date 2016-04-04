package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.CategoryDef
 */
public class CategoryDefMask
        extends AbstractDefinitionMask {

    public static CategoryDefMask forSchema(int id) {
        CategoryDefMask mask = new CategoryDefMask();
        mask.schemaId = id;
        return mask;
    }

}
