package net.bodz.bas.javadoc;

import java.lang.reflect.Method;

public interface MethodJavadoc
        extends MemberJavadoc {

    @Override
    Method getMember();

    String getReturnValueDoc();

}
