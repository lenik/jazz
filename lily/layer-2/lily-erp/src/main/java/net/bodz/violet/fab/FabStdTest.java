package net.bodz.violet.fab;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@IdType(Integer.class)
public class FabStdTest
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    FabStdTestCategory category;

    public FabStdTest() {
    }

    public FabStdTestCategory getCategory() {
        return category;
    }

    public void setCategory(FabStdTestCategory category) {
        this.category = category;
    }

}
