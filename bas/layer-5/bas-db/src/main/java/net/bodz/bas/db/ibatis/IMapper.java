package net.bodz.bas.db.ibatis;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(includeAbstract = true)
public interface IMapper {

    class fn {

        public static Class<? extends IMapper> getMapperClass(Class<?> objClass) {
            String packageName = objClass.getPackage().getName();
            String[] guesses = {
                    //
                    packageName + "." + objClass.getSimpleName() + "Mapper",
                    packageName + ".impl." + objClass.getSimpleName() + "Mapper" };

            Class<? extends IMapper> mapperClass;
            for (String guess : guesses)
                try {
                    mapperClass = (Class<? extends IMapper>) Class.forName(guess, true, objClass.getClassLoader());
                    if (mapperClass != null)
                        return mapperClass;
                } catch (ClassNotFoundException e) {
                }
            return null;
        }

    }

}
