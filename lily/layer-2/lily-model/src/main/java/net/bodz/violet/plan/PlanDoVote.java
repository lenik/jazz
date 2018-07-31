package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plando_vote")
@IdType(Integer.class)
public class PlanDoVote
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public PlanDoVote() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogVote: ...");
        return sb.toString();
    }

}
