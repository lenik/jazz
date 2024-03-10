package net.bodz.lily.schema.meta;

import java.util.List;

import javax.persistence.Table;

/**
 * @label Tag Group
 * @label.zh 标签组
 */
@Table(schema = TagGroupDef.SCHEMA_NAME, name = TagGroupDef.TABLE_NAME)
public class TagGroupDef
        extends _TagGroupDef_stuff<TagGroupDef> {

    private static final long serialVersionUID = 1L;

    private boolean ortho;

    private List<TagDef> tags;

    /**
     * Orthogonal
     *
     * @label.zh 正交
     */
    public boolean isOrtho() {
        return ortho;
    }

    public void setOrtho(boolean ortho) {
        this.ortho = ortho;
    }

    /**
     * Tag List
     *
     * @label.zh 标签列表
     */
    public List<TagDef> getTags() {
        return tags;
    }

    public void setTags(List<TagDef> tags) {
        this.tags = tags;
    }

}
