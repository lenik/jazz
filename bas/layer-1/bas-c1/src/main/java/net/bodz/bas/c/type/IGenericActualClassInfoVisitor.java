package net.bodz.bas.c.type;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public interface IGenericActualClassInfoVisitor {

    default void beginClass(GenericActualClassInfo gclass) {
    }

    default void endClass() {
    }

    default boolean beginVars() {
        return true;
    }

    default void var(int index, TypeVariable<?> var) {
    }

    default void endVars() {
    }

    default boolean beginArgs() {
        return true;
    }

    default void arg(int index, Type arg) {
    }

    default void endArgs() {
    }

    default void gsuper(GenericActualClassInfo gsuper) {
    }

    default boolean beginInterfaces() {
        return true;
    }

    default void ginterface(int index, GenericActualClassInfo ginterface) {
    }

    default void endInterfaces() {
    }

}
