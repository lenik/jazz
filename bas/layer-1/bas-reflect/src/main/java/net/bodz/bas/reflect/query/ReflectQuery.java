package net.bodz.bas.reflect.query;

/**
 * @test {@link ReflectQueryTest}
 */
public class ReflectQuery {

    public static PublicMethods selectMethodsInClass(Class<?> clazz) {
        return new PublicMethods(clazz);
    }

    public static DeclaredMethods selectDeclaredMethodsInClass(Class<?> clazz) {
        return new DeclaredMethods(clazz);
    }

    public static PublicConstructors selectConstructorsInClass(Class<?> clazz) {
        return new PublicConstructors(clazz);
    }

    public static DeclaredConstructors selectDeclaredConstructorsInClass(Class<?> clazz) {
        return new DeclaredConstructors(clazz);
    }

    public static PublicFields selectFieldsInClass(Class<?> clazz) {
        return new PublicFields(clazz);
    }

    public static DeclaredFields selectDeclaredFieldsInClass(Class<?> clazz) {
        return new DeclaredFields(clazz);
    }

}
