package net.bodz.uni.shelj.codegen.java.member;

public interface IMember
        extends
            IModifierHelper,
            IAnnotationAware {

    Class<?> getType();

    String getName();

    default boolean isJavaGetLikeAVarName() {
        return false;
    }

    String javaGet();

    default boolean isJavaSetCanHaveContext() {
        return true;
    }

    String javaSet(String javaContent);

    default String javaSet(String javaContentFormat, Object... args) {
        String content = String.format(javaContentFormat, args);
        return javaSet(content);
    }

}
