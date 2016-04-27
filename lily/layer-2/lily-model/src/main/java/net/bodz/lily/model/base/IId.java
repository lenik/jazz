package net.bodz.lily.model.base;

import net.bodz.bas.c.type.TypeParam;

public interface IId<Id> {

    Class<Id> idType();

    Id getId();

    void setId(Id id);

    class fn {

        public static <X> Class<X> getIdType(Class<? extends IId<X>> clazz) {
            IdType aIdType = clazz.getAnnotation(IdType.class);
            if (aIdType != null)
                return (Class<X>) aIdType.value();
            Class<X> decl = TypeParam.infer1(clazz, IId.class, 0);
            return decl;
        }

        @SuppressWarnings("unchecked")
        public static <X> Class<X> _getIdType(Class<?> clazz) {
            Class<? extends IId<X>> c = (Class<? extends IId<X>>) clazz;
            return getIdType(c);
        }

        @SuppressWarnings("unchecked")
        public static <Id> Id cast(Class<Id> idType, Object id) {
            if (id == null)
                return null;
            Object casted = null;
            if (idType == String.class)
                casted = id.toString();
            else if (idType == Integer.class)
                casted = ((Number) id).intValue();
            else if (idType == Long.class)
                casted = ((Number) id).longValue();
            return (Id) casted;
        }

    }

}
