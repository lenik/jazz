package net.bodz.bas.codegen.java.member;

import java.lang.reflect.Modifier;

public interface IModifierHelper {

    int getModifiers();

    default boolean isFinal() {
        return Modifier.isFinal(getModifiers());
    }

    default boolean isNotFinal() {
        return !isFinal();
    }

}
