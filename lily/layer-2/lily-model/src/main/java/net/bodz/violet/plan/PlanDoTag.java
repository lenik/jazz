package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "plandotag")
public class PlanDoTag
        extends CoTag<PlanDoTag> {

    private static final long serialVersionUID = 1L;

    public PlanDoTag() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogTag: ...");
        return sb.toString();
    }

}
