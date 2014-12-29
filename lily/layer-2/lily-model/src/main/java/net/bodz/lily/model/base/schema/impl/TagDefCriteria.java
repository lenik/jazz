package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.TagDef
 */
public class TagDefCriteria
        extends AbstractDefinitionCriteria {

    public static TagDefCriteria forSchema(int id) {
        TagDefCriteria criteria = new TagDefCriteria();
        criteria.setSchemaId(id);
        return criteria;
    }

}
