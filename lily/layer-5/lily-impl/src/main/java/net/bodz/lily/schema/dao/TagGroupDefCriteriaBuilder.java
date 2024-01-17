package net.bodz.lily.schema.dao;

/**
 * @see net.bodz.lily.schema.TagGroupDef
 */
public class TagGroupDefCriteriaBuilder
        extends AbstractDefinitionCriteriaBuilder {

    Boolean ortho;

    public Boolean getOrtho() {
        return ortho;
    }

    public TagGroupDefCriteriaBuilder setOrtho(Boolean ortho) {
        this.ortho = ortho;
        return this;
    }

    public static TagGroupDefCriteriaBuilder forSchema(int id) {
        TagGroupDefCriteriaBuilder mask = new TagGroupDefCriteriaBuilder();
        mask.schemaId = id;
        return mask;
    }

}
