package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

@IdType(Integer.class)
@Table(name = "storecat")
public class StoreCategory
        extends CoCategory<StoreCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public StoreCategory() {
        super();
    }

    public StoreCategory(StoreCategory parent) {
        super(parent);
    }

}
