package net.bodz.bas.c.reflect.query;

public class ReflectQuery {

    public static PublicMethods selectMethods(Class<?> clazz) {
        return new PublicMethods(clazz);
    }

    public static DeclaredMethods selectDeclaredMethods(Class<?> clazz) {
        return new DeclaredMethods(clazz);
    }

    public static PublicConstructors selectConstructors(Class<?> clazz) {
        return new PublicConstructors(clazz);
    }

    public static DeclaredConstructors selectDeclaredConstructors(Class<?> clazz) {
        return new DeclaredConstructors(clazz);
    }

    public static PublicFields selectFields(Class<?> clazz) {
        return new PublicFields(clazz);
    }

    public static DeclaredFields selectDeclaredFields(Class<?> clazz) {
        return new DeclaredFields(clazz);
    }

    public static ClassSelection selectClassChain(Class<?> clazz) {
        return new ClassSelection(clazz);
    }

    public static ClassSelection selectClassTree(Class<?> clazz) {
        return new ClassSelection(clazz).withInterfaces().unique();
    }

}
