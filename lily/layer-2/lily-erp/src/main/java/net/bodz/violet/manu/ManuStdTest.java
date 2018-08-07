package net.bodz.violet.manu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class ManuStdTest
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ManuStdTestCategory category;

    public ManuStdTest() {
    }

    public ManuStdTestCategory getCategory() {
        return category;
    }

    public void setCategory(ManuStdTestCategory category) {
        this.category = category;
    }

}
