package net.bodz.violet.sale;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

@IdType(Integer.class)
@Table(name = "salecat")
public class SalesCategory
        extends CoNode<SalesCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public SalesCategory() {
        super();
    }

    public SalesCategory(SalesCategory parent) {
        super(parent);
    }

}
