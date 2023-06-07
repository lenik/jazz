package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoPhase;

@IdType(Integer.class)
@Table(name = "salephase")
public class SalesPhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public SalesPhase() {
        super();
    }

}
