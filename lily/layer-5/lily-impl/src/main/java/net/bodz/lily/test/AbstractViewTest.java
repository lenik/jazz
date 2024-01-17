package net.bodz.lily.test;

import net.bodz.bas.db.ibatis.IGenericMapper;

public abstract class AbstractViewTest<T, mapper_t extends IGenericMapper<T>>
        extends AbstractTableViewTest<T, mapper_t> {

}
