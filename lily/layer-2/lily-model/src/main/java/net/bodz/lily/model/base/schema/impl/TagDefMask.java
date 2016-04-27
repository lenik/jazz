package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;

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
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
        tagGroupId = map.getInt("tagv", tagGroupId);
    }

}
