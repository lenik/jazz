package net.bodz.violet;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.model.base.CoObject;
import net.bodz.lily.test.AbstractTableTest;

public abstract class VioletMapperTest<T extends CoObject, M, mapper_t extends IMapperTemplate<T, M>>
        extends AbstractTableTest<T, M, mapper_t> {

    public VioletMapperTest() {
        super();
    }

    @Override
    protected DataContext getContext() {
        return DataHub.getPreferredHub().getTest();
    }

}
