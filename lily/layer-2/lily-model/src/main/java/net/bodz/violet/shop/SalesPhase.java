package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

@IdType(Integer.class)
@Table(name = "salephase")
public class SalesPhase
        extends CoCode<SalesPhase> {

    private static final long serialVersionUID = 1L;

    public SalesPhase() {
        super();
    }

    public SalesPhase(SalesPhase parent) {
        super(parent);
    }

}
