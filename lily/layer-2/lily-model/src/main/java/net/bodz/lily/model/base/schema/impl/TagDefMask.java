package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.schema.TagDef
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

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        tagGroupId = map.getInt("tagv", tagGroupId);
    }

}
