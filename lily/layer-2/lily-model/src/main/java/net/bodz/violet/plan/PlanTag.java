package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

@IdType(Integer.class)
@Table(name = "plantag")
public class PlanTag
        extends CoNode<PlanTag, Integer> {

    private static final long serialVersionUID = 1L;

    public PlanTag() {
        super();
    }

    public PlanTag(PlanTag parent) {
        super(parent);
    }

}
