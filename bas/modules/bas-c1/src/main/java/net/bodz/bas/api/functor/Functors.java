package net.bodz.bas.api.functor;

public class Functors {

    @SuppressWarnings("unchecked")
    public static <T> T eval(Object o) throws EvalException {
        if (o instanceof Functor)
            return eval((Functor<T>) o);
        return (T) o;
    }

    public static <T> T eval(Functor<T> f) throws EvalException {
        return f.eval();
    }

    public static <T> T eval(Functor<T> f, Object... args) throws EvalException {
        EvalContext context = EvalContext.getInstance();
        context.enter(args);
        T val = f.eval();
        context.leave();
        return val;
    }

    public static <T> T value(Object o) {
        try {
            return eval(o);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T value(Functor<T> f) {
        try {
            return eval(f);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T value(Functor<T> f, Object... args) {
        try {
            return eval(f, args);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
