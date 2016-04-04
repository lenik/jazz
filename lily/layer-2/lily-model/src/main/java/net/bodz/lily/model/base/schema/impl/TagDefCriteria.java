package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;
import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.schema.TagDef
 */
public class TagDefCriteria
        extends AbstractDefinitionCriteria {

    public Integer tagGroupId;

    public static TagDefCriteria forSchema(int id) {
        TagDefCriteria criteria = new TagDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

    public static TagDefCriteria forTagGroup(int tagGroupId) {
        TagDefCriteria criteria = new TagDefCriteria();
        criteria.tagGroupId = tagGroupId;
        return criteria;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        tagGroupId = map.getInt("tagv", tagGroupId);
    }

}
