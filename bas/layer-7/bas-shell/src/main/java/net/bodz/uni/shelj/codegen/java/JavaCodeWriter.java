package net.bodz.uni.shelj.codegen.java;

import net.bodz.bas.codegen.JavaSourceWriter;
import net.bodz.bas.io.ITreeOut;
import net.bodz.uni.shelj.codegen.java.member.IMember;

public class JavaCodeWriter
        extends JavaSourceWriter {

    private static final long serialVersionUID = 1L;

    String TEMPLATE_PLACEHOLDER = "\\?";

    public JavaCodeWriter(String packageName, ITreeOut _orig) {
        super(packageName, _orig);
    }

    String makeStatementWithJavaGet(IMember member, String templateFormat, Object... args) {
        String template = String.format(templateFormat, args);
        return makeStatementWithJavaGet(member, template);
    }

    String makeStatementWithJavaGet(IMember member, String template) {
        String body = member.javaGet();
        if (body == null)
            return null;
        return template.replace(TEMPLATE_PLACEHOLDER, body);
    }

    public void printLineWithJavaGet(IMember member, String templateFormat, Object... args) {
        String template = String.format(templateFormat, args);
        printLineWithJavaGet(member, template);
    }

    public void printLineWithJavaGet(IMember member, String template) {
        String statement = makeStatementWithJavaGet(member, template);
        if (statement != null) {
            this.println(statement);
        } else {
            this.printf("// no-get: %s\n", member.getName());
        }
    }

    public void printLineForJavaSet(IMember member, String javaContentFormat, Object... args) {
        String content = String.format(javaContentFormat, args);
        printLineForJavaSet(member, content);
    }

    public void printLineForJavaSet(IMember member, String javaContent) {
        String body = member.javaSet(javaContent);
        if (body != null) {
            this.println(body + ";");
        } else {
            this.printf("// no-set: %s <-- %s\n", member.getName(), javaContent);
        }
    }

}
