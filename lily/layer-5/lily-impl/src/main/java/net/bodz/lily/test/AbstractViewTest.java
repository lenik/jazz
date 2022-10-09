package net.bodz.lily.test;

import net.bodz.bas.db.ibatis.IGenericMapper;

public abstract class AbstractViewTest<T, M, mapper_t extends IGenericMapper<T, M>>
        extends AbstractTableViewTest<T, M, mapper_t> {

}
