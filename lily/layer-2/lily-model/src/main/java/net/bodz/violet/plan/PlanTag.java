package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "plantag")
public class PlanTag
        extends CoTag<PlanTag> {

    private static final long serialVersionUID = 1L;

    public PlanTag() {
        super();
    }

    public PlanTag(PlanTag parent) {
        super(parent);
    }

}
