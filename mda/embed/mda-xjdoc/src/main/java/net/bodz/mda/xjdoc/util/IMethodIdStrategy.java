package net.bodz.mda.xjdoc.util;

import java.lang.reflect.Method;

import com.thoughtworks.qdox.model.JavaMethod;

public interface IMethodIdStrategy {

    String getMethodId(Method method);

    String getMethodId(JavaMethod method);

}
