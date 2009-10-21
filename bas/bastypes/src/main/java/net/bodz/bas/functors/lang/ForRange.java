package net.bodz.bas.functors.lang;

import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang2.EvalException;
import net.bodz.bas.lang2.FunctorException;
import net.bodz.bas.nls.FPNLS;

public class ForRange<T> extends _Functor<T> {

    _Functor<Number> from;
    _Functor<Number> to;
    Number           step;
    _Functor<?>      body;

    Object           index;

    public class Index extends TransientConstant<Object> {
        @Override
        public Object eval2() {
            return index;
        }
    }

    public ForRange() {
        this.step = 1;
    }

    public ForRange(_Functor<Number> from, _Functor<Number> to, Number step, _Functor<T> body) {
        setFrom(from);
        setTo(to);
        setStep(step);
        setBody(body);
    }

    public ForRange(_Functor<Number> from, _Functor<Number> to, Number step) {
        this(from, to, step, null);
    }

    public ForRange(_Functor<Number> from, _Functor<Number> to, _Functor<T> body) {
        this(from, to, 1, body);
    }

    public ForRange(_Functor<Number> from, _Functor<Number> to) {
        this(from, to, 1, null);
    }

    public ForRange<T> setFrom(_Functor<Number> from) {
        this.from = from;
        if (from != null)
            from.setOuter(this);
        return this;
    }

    public ForRange<T> setTo(_Functor<Number> to) {
        this.to = to;
        if (to != null)
            to.setOuter(this);
        return this;
    }

    public ForRange<T> setStep(Number step) {
        this.step = step;
        return this;
    }

    public ForRange<T> setBody(_Functor<T> body) {
        this.body = body;
        if (body != null)
            body.setOuter(this);
        return this;
    }

    public Index index() {
        return new Index();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T eval2() throws Control, EvalException {
        Number nFrom = from.eval();
        Number nTo = to.eval();

        if (body == null)
            return null;

        Object last = null;

        if (nFrom instanceof Double || nTo instanceof Double) {
            double l = nFrom.doubleValue();
            double r = nTo.doubleValue();
            double s = step.doubleValue();

            while (l <= r) {
                index = l;
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                } catch (ControlContinue controlContinue) {
                    if (controlContinue.matches(this)) {
                        l += s;
                        continue;
                    }
                    throw controlContinue;
                }
                l += s;
            }
            return (T) last;
        }

        if (nFrom instanceof Float || nTo instanceof Float) {
            float l = nFrom.floatValue();
            float r = nTo.floatValue();
            float s = step.floatValue();

            while (l <= r) {
                index = l;
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                } catch (ControlContinue controlContinue) {
                    if (controlContinue.matches(this)) {
                        l += s;
                        continue;
                    }
                    throw controlContinue;
                }
                l += s;
            }
            return (T) last;
        }

        if (nFrom instanceof Long || nTo instanceof Long) {
            long l = nFrom.longValue();
            long r = nTo.longValue();
            long s = step.longValue();

            while (l <= r) {
                index = l;
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                } catch (ControlContinue controlContinue) {
                    if (controlContinue.matches(this)) {
                        l += s;
                        continue;
                    }
                    throw controlContinue;
                }
                l += s;
            }
            return (T) last;
        }

        int l = nFrom.intValue();
        int r = nTo.intValue();
        int s = step.intValue();

        while (l <= r) {
            index = l;
            try {
                last = body.eval();
            } catch (ControlBreak controlBreak) {
                if (controlBreak.matches(this))
                    break;
                throw controlBreak;
            } catch (ControlContinue controlContinue) {
                if (controlContinue.matches(this)) {
                    l += s;
                    continue;
                }
                throw controlContinue;
            }
            l += s;
        }
        return (T) last;
    }

    @SuppressWarnings("unchecked")
    @Override
    public _Functor<?> reduce() throws FunctorException {
        if (isReduced())
            return this;

        if (body != null)
            body = (_Functor<?>) body.reduce();

        if (from != null)
            from = (_Functor<Number>) from.reduce();
        if (to != null)
            to = (_Functor<Number>) to.reduce();
        if (from instanceof _Functor && to instanceof _Functor) {
            _Functor<Number> afFrom = (_Functor<Number>) from;
            _Functor<Number> afTo = (_Functor<Number>) to;
            if (afFrom.isEvaluated() && afTo.isEvaluated()) {
                try {
                    Number nFrom = afFrom.eval();
                    Number nTo = afTo.eval();
                    // if (nFrom.equals(nTo))
                    // return body;
                    if (nFrom.doubleValue() > nTo.doubleValue())
                        return null;
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
            }
            if (body == null)
                if (afFrom.isClosed() && afTo.isClosed())
                    return null;
        }

        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (!isValidated()) {
            if (Math.abs(step.floatValue()) <= Float.MIN_VALUE)
                throw new ValidationException(FPNLS.getString("ForRange.stepTooSmall") + step); //$NON-NLS-1$
            if (step instanceof Float)
                if (((Float) step).isNaN())
                    throw new ValidationException(FPNLS.getString("ForRange.stepIsntNum")); //$NON-NLS-1$
            if (step instanceof Double)
                if (((Double) step).isNaN())
                    throw new ValidationException(FPNLS.getString("ForRange.stepIsntNum")); //$NON-NLS-1$
            if (from != null)
                from.validate();
            if (to != null)
                to.validate();
            if (body != null)
                body.validate();
            setFlagBits(VALIDATED);
        }
    }

    private static ThreadLocal<ForRange<?>> tlBuilt = new ThreadLocal<ForRange<?>>();

    public static <T> ForRange<T> build(_Functor<Number> from, _Functor<Number> to, _Functor<T> body) {
        ForRange<T> built = built();
        tlBuilt.set(null);
        built.setFrom(from);
        built.setTo(to);
        built.setBody(body);
        return built;
    }

    @SuppressWarnings("unchecked")
    public static <T> ForRange<T> built() {
        ForRange<T> built = (ForRange<T>) tlBuilt.get();
        if (built == null) {
            built = new ForRange<T>();
            tlBuilt.set(built);
        }
        return built;
    }

}
