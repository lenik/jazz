package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.VoteRecord;

@Table(name = "plan_vote")
public class PlanVote
        extends VoteRecord {

    private static final long serialVersionUID = 1L;

    Plan plan;

    public PlanVote() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
