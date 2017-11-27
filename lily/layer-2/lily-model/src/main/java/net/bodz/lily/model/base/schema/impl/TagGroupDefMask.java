package net.bodz.lily.model.base.schema.impl;

/**
 * @see net.bodz.lily.model.base.schema.TagGroupDef
 */
public class TagGroupDefMask
        extends AbstractDefinitionMask {

    Boolean ortho;

    public Boolean getOrtho() {
        return ortho;
    }

    public TagGroupDefMask setOrtho(Boolean ortho) {
        this.ortho = ortho;
        return this;
    }

    public static TagGroupDefMask forSchema(int id) {
        TagGroupDefMask mask = new TagGroupDefMask();
        mask.schemaId = id;
        return mask;
    }

}
