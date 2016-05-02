package net.bodz.bas.db.ibatis;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(includeAbstract = true)
public interface IMapper {

    class fn {

        static ITypeMapper[] mapperTmaps = {
                // TODO cache-enable?
                new NameConventionTypeMapper(null, "Mapper", false), //
                new NameConventionTypeMapper("impl.", "Mapper", false), //
                new NameConventionTypeMapper(null, 1, "db.", "Mapper", false), //
        };

        public static Class<? extends IMapper> getMapperClass(Class<?> objClass) {
            Class<? extends IMapper> mapperClass;
            for (ITypeMapper tmap : mapperTmaps) {
                mapperClass = (Class<? extends IMapper>) tmap.map(objClass);
                if (mapperClass != null)
                    return mapperClass;
            }
            return null;
        }

    }

}
