package net.bodz.bas.db.ibatis;

import net.bodz.bas.c.type.TypeNearby;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(includeAbstract = true)
public interface IMapper {

    class fn {

        static TypeNearby[] nearbies = {
                // TODO cache-enable?
                new TypeNearby(null, null, "Mapper", false), //
                new TypeNearby(null, "impl.", "Mapper", false), //
        };

        public static Class<? extends IMapper> getMapperClass(Class<?> objClass) {
            Class<? extends IMapper> mapperClass;
            for (TypeNearby nearby : nearbies) {
                mapperClass = (Class<? extends IMapper>) nearby.find(objClass);
                if (mapperClass != null)
                    return mapperClass;
            }
            return null;
        }

    }

}
