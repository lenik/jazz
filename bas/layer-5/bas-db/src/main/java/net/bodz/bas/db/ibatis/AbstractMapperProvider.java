package net.bodz.bas.db.ibatis;

public abstract class AbstractMapperProvider
        implements IMapperProvider {

    @Override
    public <M extends IMapper> M getMapper(Class<M> mapperClass) {
        return getMapper(mapperClass, true);
    }

}
