package net.bodz.swt.viz.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;

import net.bodz.swt.viz.form.vbo.StringVbo;

@form.view.builder(StringVbo.class)
public @interface form {

    @interface view {

        @Retention(RetentionPolicy.RUNTIME)
        @interface builder {
            Class<?> value();

            class fn {
                public static Class<?> getValue(AnnotatedElement elm) {
                    builder _builder = elm.getAnnotation(builder.class);
                    return _builder == null ? null : _builder.value();
                }
            }

        }

    }

}
