package net.bodz.bas.closure.eval;

public class Evaluables {

    @SuppressWarnings("unchecked")
    public static <T> T eval(Object o)
            throws EvalException {
        if (o instanceof IEvaluable)
            return eval((IEvaluable<T>) o);
        return (T) o;
    }

    public static <T> T eval(IEvaluable<T> f)
            throws EvalException {
        return f.eval();
    }

    public static <T> T eval(IEvaluable<T> f, Object... args)
            throws EvalException {
        EvalContext context = EvalContext.getInstance();
        context.enter(args);
        T val;
        try {
            val = f.eval();
        } finally {
            context.leave();
        }
        return val;
    }

    public static <T> T value(Object o) {
        try {
            return eval(o);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T value(IEvaluable<T> f) {
        try {
            return eval(f);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T value(IEvaluable<T> f, Object... args) {
        try {
            return eval(f, args);
        } catch (EvalException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
