package net.bodz.bas.db.ctx;

import org.apache.ibatis.session.SqlSession;

import net.bodz.bas.meta.decl.NotNull;

public interface IDataContextAware {

    @NotNull
    DataContext getDataContext();

    void setDataContext(@NotNull DataContext dataContext);

    default <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass) {
        return getDataContext().getMapper(mapperClass);
    }

    default <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass, boolean batch) {
        return getDataContext().getMapper(mapperClass, batch);
    }

    default <mapper_t> mapper_t getMapper(Class<mapper_t> mapperClass, SqlSession session) {
        return getDataContext().getMapper(mapperClass, session);
    }

}
