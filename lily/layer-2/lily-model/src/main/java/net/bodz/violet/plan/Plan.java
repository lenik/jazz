package net.bodz.violet.plan;

import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.mx.CoMessage;

@IdType(Long.class)
@Table(name = "plan")
public class Plan
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    private PlanCategory category;
    private PlanPhase phase;
    private Set<PlanTag> tags;

    /**
     * @label Category
     * @label.zh 分类
     */
    @OfGroup(StdGroup.Classification.class)
    public PlanCategory getCategory() {
        return category;
    }

    public void setCategory(PlanCategory category) {
        this.category = category;
    }

    @OfGroup(StdGroup.Status.class)
    public PlanPhase getPhase() {
        return phase;
    }

    public void setPhase(PlanPhase phase) {
        this.phase = phase;
    }

    @OfGroup(StdGroup.Classification.class)
    public Set<PlanTag> getTags() {
        return tags;
    }

    public void setTags(Set<PlanTag> tags) {
        this.tags = tags;
    }

}
