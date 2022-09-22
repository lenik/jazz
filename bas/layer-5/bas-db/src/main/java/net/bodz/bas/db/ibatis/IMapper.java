package net.bodz.bas.db.ibatis;

import net.bodz.bas.c.type.IClassFunction;
import net.bodz.bas.c.type.ModifiedClassNameResolver;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(includeAbstract = true)
public interface IMapper {

    class fn {

        static IClassFunction[] mapperClassResolvers = {
                // TODO cache-enable?
                new ModifiedClassNameResolver(null, "Mapper", false), //
                new ModifiedClassNameResolver("dao.", "Mapper", false), //
                new ModifiedClassNameResolver("impl.", "Mapper", false), //
                new ModifiedClassNameResolver(null, 1, "db.", "Mapper", false), //
        };

        public static <M extends IMapper> Class<M> requireMapperClass(Class<?> objClass) {
            Class<M> mapperClass = getMapperClass(objClass);
            if (mapperClass == null)
                throw new IllegalArgumentException("unmapped entity: " + objClass);
            return mapperClass;
        }

        @SuppressWarnings("unchecked")
        public static <M extends IMapper> Class<M> getMapperClass(Class<?> objClass) {
            Class<? extends IMapper> mapperClass;
            for (IClassFunction resolver : mapperClassResolvers) {
                mapperClass = (Class<? extends IMapper>) resolver.apply(objClass);
                if (mapperClass != null)
                    return (Class<M>) mapperClass;
            }
            return null;
        }

    }

}
