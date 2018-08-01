package net.bodz.lily.schema.impl;

/**
 * @see net.bodz.lily.schema.TagDef
 */
public class TagDefMask
        extends AbstractDefinitionMask {

    public Integer tagGroupId;

    public static TagDefMask forSchema(int id) {
        TagDefMask mask = new TagDefMask();
        mask.schemaId = id;
        return mask;
    }

    public static TagDefMask forTagGroup(int tagGroupId) {
        TagDefMask mask = new TagDefMask();
        mask.tagGroupId = tagGroupId;
        return mask;
    }

}
