package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.FavRecord;

@Table(name = "plan_fav")
public class PlanFav
        extends FavRecord {

    private static final long serialVersionUID = 1L;

    Plan plan;

    public PlanFav() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
