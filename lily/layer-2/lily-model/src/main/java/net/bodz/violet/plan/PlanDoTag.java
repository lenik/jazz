package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plandotag")
@IdType(Integer.class)
public class PlanDoTag
        extends CoEntity<Integer> {

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
