package net.bodz.violet.schema.plan;

import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;

@Table(schema = Plan.SCHEMA_NAME, name = Plan.TABLE_NAME)
public class Plan
        extends _Plan_stuff {

    private static final long serialVersionUID = 1L;

    private Set<PlanTag> tags;

    @OfGroup(StdGroup.Classification.class)
    public Set<PlanTag> getTags() {
        return tags;
    }

    public void setTags(Set<PlanTag> tags) {
        this.tags = tags;
    }
}
