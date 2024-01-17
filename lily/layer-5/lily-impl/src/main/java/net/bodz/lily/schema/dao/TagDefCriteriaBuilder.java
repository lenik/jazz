package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.TagDef
 */
public class TagDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    public Integer tagGroupId;

    public static TagDefCriteriaBuilder forSchema(int id) {
        TagDefCriteriaBuilder mask = new TagDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

    public static TagDefCriteriaBuilder forTagGroup(int tagGroupId) {
        TagDefCriteriaBuilder mask = new TagDefCriteriaBuilder();
        mask.tagGroupId = tagGroupId;
        return mask;
    }

}
