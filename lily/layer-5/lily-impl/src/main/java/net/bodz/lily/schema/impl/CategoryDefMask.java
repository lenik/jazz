package net.bodz.lily.schema.impl;

/**
 * @see net.bodz.lily.schema.CategoryDef
 */
public class CategoryDefMask
        extends AbstractDefinitionMask {

    public static CategoryDefMask forSchema(int id) {
        CategoryDefMask mask = new CategoryDefMask();
        mask.schemaId = id;
        return mask;
    }

}
