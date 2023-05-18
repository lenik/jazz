package net.bodz.uni.shelj.codegen.java.member;

import net.bodz.bas.io.IPrintOut;

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

    default String makeStatementWithJavaGet(String templateFormat, Object... args) {
        String template = String.format(templateFormat, args);
        return makeStatementWithJavaGet(template);
    }

    String TEMPLATE_PLACEHOLDER = "\\?";

    default String makeStatementWithJavaGet(String template) {
        String body = javaGet();
        if (body == null)
            return null;
        return template.replace(TEMPLATE_PLACEHOLDER, body);
    }

    default void printStatementLineWithJavaGet(IPrintOut out, String templateFormat, Object... args) {
        String template = String.format(templateFormat, args);
        printStatementLineWithJavaGet(out, template);
    }

    default void printStatementLineWithJavaGet(IPrintOut out, String template) {
        String statement = makeStatementWithJavaGet(template);
        if (statement != null)
            out.println(statement);
    }

    default boolean isJavaSetCanHaveContext() {
        return true;
    }

    String javaSet(String javaContent);

    default String javaSet(String javaContentFormat, Object... args) {
        String content = String.format(javaContentFormat, args);
        return javaSet(content);
    }

    default void printLineForJavaSet(IPrintOut out, String javaContentFormat, Object... args) {
        String content = String.format(javaContentFormat, args);
        printLineForJavaSet(out, content);
    }

    default void printLineForJavaSet(IPrintOut out, String javaContent) {
        String body = javaSet(javaContent);
        if (body != null)
            out.println(body + ";");
    }

}
