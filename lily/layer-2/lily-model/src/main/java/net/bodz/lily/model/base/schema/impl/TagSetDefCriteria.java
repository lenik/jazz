package net.bodz.lily.model.base.schema.impl;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

/**
 * @see net.bodz.lily.model.base.schema.TagSetDef
 */
public class TagSetDefCriteria
        extends AbstractDefinitionCriteria {

    Boolean ortho;

    public Boolean getOrtho() {
        return ortho;
    }

    public TagSetDefCriteria setOrtho(Boolean ortho) {
        this.ortho = ortho;
        return this;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        ortho = map.getBoolean("ortho", ortho);
    }

    public static TagSetDefCriteria forSchema(int id) {
        TagSetDefCriteria criteria = new TagSetDefCriteria();
        criteria.schemaId = id;
        return criteria;
    }

}
