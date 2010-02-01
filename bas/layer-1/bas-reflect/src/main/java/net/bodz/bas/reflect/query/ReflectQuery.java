package net.bodz.bas.reflect.query;

/**
 * @test {@link ReflectQueryTest}
 */
public class ReflectQuery {

    public static MethodSelection selectMethodsInClass(Class<?> clazz) {
        return new PublicMethods(clazz);
    }

    public static MethodSelection selectDeclaredMethodsInClass(Class<?> clazz) {
        return new DeclaredMethods(clazz);
    }

}
