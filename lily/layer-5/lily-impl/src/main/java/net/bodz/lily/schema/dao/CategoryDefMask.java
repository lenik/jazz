package net.bodz.lily.schema.dao;

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
