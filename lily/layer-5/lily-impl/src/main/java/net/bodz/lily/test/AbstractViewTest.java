package net.bodz.lily.test;

import net.bodz.bas.db.ibatis.IGenericMapper;
import net.bodz.lily.model.base.CoObject;

public abstract class AbstractViewTest<T extends CoObject, M, mapper_t extends IGenericMapper<T, M>>
        extends AbstractTableViewTest<T, M, mapper_t> {

}
