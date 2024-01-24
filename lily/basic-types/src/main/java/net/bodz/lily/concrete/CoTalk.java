package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_REC })
@IdType(Long.class)
public abstract class CoTalk<self_t extends CoTalk<self_t>>
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    self_t parent;
    Long parentId;

    /**
     *
     * @label parent
     */
    public self_t getParent() {
        return parent;
    }

    /**
     */
    public void setParent(@NotNull self_t value) {
        this.parent = value;
    }

    @Precision(value = 19)
    @Column(name = "parent")
    public synchronized Long getParentId() {
        if (parent != null) {
            if (parent.getId() == null)
                return null;
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Long value) {
        this.parentId = value;
    }

}
