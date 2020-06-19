package net.bodz.violet.plan;

import java.util.List;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@Table(name = "plando")
@IdType(Long.class)
public class PlanDo
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Plan plan;
    PlanDo parent;
    List<String> changes;

    public PlanDo() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public PlanDo getParent() {
        return parent;
    }

    public void setParent(PlanDo parent) {
        this.parent = parent;
    }

    public List<String> getChanges() {
        return changes;
    }

    public void setChanges(List<String> changes) {
        this.changes = changes;
    }

}
