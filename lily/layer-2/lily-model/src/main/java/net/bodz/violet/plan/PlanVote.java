package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plan_vote")
@IdType(Long.class)
public class PlanVote
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public PlanVote() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planVote: ...");
        return sb.toString();
    }

}
