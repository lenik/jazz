package net.bodz.bas.meta.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ITyperFamily;
import net.bodz.bas.typer.std.IValidator;

public @interface typer {

    /**
     * TyperClass can be defined on:
     * 
     * <table>
     * <tr>
     * <th>{@link ElementType}</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>{@link ElementType#TYPE Type}</td>
     * <td>Define the static typers for types.</td>
     * </tr>
     * <tr>
     * <td>{@link ElementType#FIELD Field}</td>
     * <td>Define typers preferences for fields.</td>
     * </tr>
     * <tr>
     * <td>{@link ElementType#METHOD Method}</td>
     * <td>Define typers preferences for properties.</td>
     * </tr>
     * <tr>
     * <td>{@link ElementType#PARAMETER Parameter}</td>
     * <td>Define typers preferences for parameters.</td>
     * </tr>
     * <tr>
     * <td>{@link ElementType#LOCAL_VARIABLE Local Variable}</td>
     * <td>Define typers preferences for local variable. (But how to get it?)</td>
     * </tr>
     * <tr>
     * <td>{@link ElementType#TYPE_PARAMETER Type Parameter}</td>
     * <td>Define theh static typers for type parameter. (Since Java 7)</td>
     * </tr>
     * </table>
     */
    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface family {

        Class<? extends ITyperFamily<?>> value();

        class fn {
            public static Class<? extends ITyperFamily<?>> getValue(AnnotatedElement elm) {
                family _family = elm.getAnnotation(family.class);
                return _family == null ? null : _family.value();
            }
        }

    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface parser {

        Class<? extends IParser<?>> value();

        class fn {
            public static Class<? extends IParser<?>> getValue(AnnotatedElement elm) {
                parser _parser = elm.getAnnotation(parser.class);
                return _parser == null ? null : _parser.value();
            }
        }

    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface formatter {

        Class<? extends IFormatter<?>> value();

        class fn {
            public static Class<? extends IFormatter<?>> getValue(AnnotatedElement elm) {
                formatter _formatter = elm.getAnnotation(formatter.class);
                return _formatter == null ? null : _formatter.value();
            }
        }

    }

    @Documented
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @interface validator {

        Class<? extends IValidator<?>> value();

        class fn {
            public static Class<? extends IValidator<?>> getValue(AnnotatedElement elm) {
                validator _validator = elm.getAnnotation(validator.class);
                return _validator == null ? null : _validator.value();
            }
        }

    }

}