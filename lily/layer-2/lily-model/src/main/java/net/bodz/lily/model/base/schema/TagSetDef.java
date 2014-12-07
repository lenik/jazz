package net.bodz.lily.model.base.schema;

import java.util.List;

public class TagSetDef
        extends AbstractDefinition<TagSetDef> {

    private static final long serialVersionUID = 1L;

    private boolean ortho;
    private List<TagDef> tags;

    public boolean isOrtho() {
        return ortho;
    }

    public void setOrtho(boolean ortho) {
        this.ortho = ortho;
    }

    public List<TagDef> getTags() {
        return tags;
    }

    public void setTags(List<TagDef> tags) {
        this.tags = tags;
    }

}
