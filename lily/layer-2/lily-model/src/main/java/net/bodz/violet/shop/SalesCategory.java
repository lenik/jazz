package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

@IdType(Integer.class)
@Table(name = "salecat")
public class SalesCategory
        extends CoCategory<SalesCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public SalesCategory() {
        super();
    }

    public SalesCategory(SalesCategory parent) {
        super(parent);
    }

}
