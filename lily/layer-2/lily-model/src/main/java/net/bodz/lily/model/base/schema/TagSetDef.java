package net.bodz.lily.model.base.schema;

import java.util.List;

import javax.persistence.Table;

/**
 * @label Tag Set
 * @label.zh 标签集
 */
@Table(name = "tagv")
public class TagSetDef
        extends AbstractDefinition<TagSetDef> {

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
