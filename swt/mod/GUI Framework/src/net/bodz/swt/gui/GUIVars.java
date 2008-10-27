package net.bodz.swt.gui;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.ref.Vars.ConstantMeta;
import net.bodz.bas.lang.ref.Vars.ConstantVar;
import net.bodz.bas.lang.ref.Vars.FieldMeta;
import net.bodz.bas.lang.ref.Vars.FieldVar;
import net.bodz.bas.lang.ref.Vars.PropertyMeta;
import net.bodz.bas.lang.ref.Vars.PropertyVar;
import net.bodz.bas.types.Checker;
import net.bodz.bas.types.Checks;
import net.bodz.bas.types.util.Types;

public class GUIVars {

    public static class GUIFieldMeta extends FieldMeta implements GUIVarMeta {

        protected final Checker checker;

        public GUIFieldMeta(Field field) {
            super(field);
            try {
                checker = Checks.getChecker(this);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public GUIHint getHint() {
            return GUIHint.get(field);
        }

        public void check(Object value) throws CheckException {
            Class<?> type = getType();
            if (value != null && !Types.box(type).isInstance(value))
                throw new CheckException("Not a instanceof " + type + ": "
                        + value);
            if (checker != null)
                checker.check(value);
        }
    }

    public static class GUIFieldVar<T> extends FieldVar<T> implements GUIVar<T> {

        public GUIFieldVar(GUIFieldMeta meta, Object object) {
            super(meta, object);
        }

        public GUIFieldVar(Field field, Object object) {
            super(new GUIFieldMeta(field), object);
        }

        @Override
        public GUIVarMeta getMeta() {
            return (GUIVarMeta) super.getMeta();
        }

        @Override
        public void check(Object newValue) throws CheckException {
            GUIFieldMeta meta = (GUIFieldMeta) this.meta;
            meta.check(newValue);
        }

    }

    public static class GUIPropertyMeta extends PropertyMeta implements
            GUIVarMeta {

        protected final Checker checker;

        public GUIPropertyMeta(PropertyDescriptor property) {
            super(property);
            try {
                checker = Checks.getChecker(this);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public GUIHint getHint() {
            return GUIHint.get(property);
        }

        public void check(Object value) throws CheckException {
            Class<?> type = getType();
            if (value != null && !Types.box(type).isInstance(value))
                throw new CheckException("Not a instanceof " + type + ": "
                        + value);
            if (checker != null)
                checker.check(value);
        }

    }

    public static class GUIPropertyVar<T> extends PropertyVar<T> implements
            GUIVar<T> {

        public GUIPropertyVar(PropertyMeta meta, Object object) {
            super(meta, object);
        }

        public GUIPropertyVar(PropertyDescriptor property, Object object) {
            super(new GUIPropertyMeta(property), object);
        }

        @Override
        public GUIVarMeta getMeta() {
            return (GUIVarMeta) super.getMeta();
        }

        @Override
        public void check(Object newValue) throws CheckException {
            GUIPropertyMeta meta = (GUIPropertyMeta) this.meta;
            meta.check(newValue);
        }

    }

    public static class GUIConstantMeta extends ConstantMeta implements
            GUIVarMeta {

        private final GUIHint   hint;
        protected final Checker checker;

        public GUIConstantMeta(Class<?> type, GUIHint hint) {
            super(type);
            this.hint = hint;
            try {
                this.checker = Checks.getChecker(this);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        }

        public GUIConstantMeta(String name, Class<?> type, GUIHint hint) {
            super(name, type);
            this.hint = hint;
            try {
                this.checker = Checks.getChecker(this);
            } catch (CreateException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public GUIHint getHint() {
            return hint;
        }

        public void check(Object value) throws CheckException {
            Class<?> type = getType();
            if (value != null && !Types.box(type).isInstance(value))
                throw new CheckException("Not a instanceof " + type + ": "
                        + value);
            if (checker != null)
                checker.check(value);
        }

    }

    public static class GUIConstantVar<T> extends ConstantVar<T> implements
            GUIVar<T> {

        public GUIConstantVar(String name, T value, GUIHint hint) {
            super(new GUIConstantMeta(name, value.getClass(), hint), value);
        }

        public GUIConstantVar(String name, T value) {
            super(new GUIConstantMeta(name, value.getClass(), null), value);
        }

        public GUIConstantVar(T value, GUIHint hint) {
            super(new GUIConstantMeta(value.getClass(), hint), value);
        }

        public GUIConstantVar(T value) {
            super(new GUIConstantMeta(value.getClass(), null), value);
        }

        @Override
        public GUIVarMeta getMeta() {
            return (GUIVarMeta) super.getMeta();
        }

        @Override
        public void check(Object newValue) throws CheckException {
            GUIConstantMeta meta = (GUIConstantMeta) this.meta;
            meta.check(newValue);
        }

    }

    public static <T> GUIVar<T> wrap(Object object, Field field) {
        return new GUIFieldVar<T>(field, object);
    }

    public static <T> GUIVar<T> wrap(Object object, PropertyDescriptor property) {
        return new GUIPropertyVar<T>(property, object);
    }

    public static <T> GUIVar<T> wrap(String name, T constant, GUIHint hint) {
        return new GUIConstantVar<T>(name, constant, hint);
    }

    public static <T> GUIVar<T> wrap(String name, T constant) {
        return new GUIConstantVar<T>(name, constant);
    }

    public static <T> GUIVar<T> wrap(T constant, GUIHint hint) {
        return new GUIConstantVar<T>(constant, hint);
    }

    public static <T> GUIVar<T> wrap(T constant) {
        return new GUIConstantVar<T>(constant);
    }

}
