package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.schema.TagGroupDef
 */
public class TagGroupDefCriteria
        extends AbstractDefinitionCriteria {

    Boolean ortho;

    public Boolean getOrtho() {
        return ortho;
    }

    public TagGroupDefCriteria setOrtho(Boolean ortho) {
        this.ortho = ortho;
        return this;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        ortho = map.getBoolean("ortho", ortho);
    }

    public static TagGroupDefCriteria forSchema(int id) {
        TagGroupDefCriteria criteria = new TagGroupDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
