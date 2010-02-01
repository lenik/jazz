package net.bodz.bas.reflect.query;

/**
 * @test {@link ReflectQueryTest}
 */
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

}
