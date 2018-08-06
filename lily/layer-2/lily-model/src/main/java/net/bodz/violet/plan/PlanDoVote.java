package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "plando_vote")
public class PlanDoVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    PlanDo planDo;

    public PlanDoVote() {
    }

    public PlanDo getPlanDo() {
        return planDo;
    }

    public void setPlanDo(PlanDo planDo) {
        this.planDo = planDo;
    }

}
