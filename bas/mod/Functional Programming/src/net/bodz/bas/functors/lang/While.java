package net.bodz.bas.functors.lang;

import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;

public class While<T> extends _Functor<T> {

    protected _Functor<Boolean> predicate;
    protected _Functor<T>       body;

    protected T                 evaluated;

    public While() {
    }

    public While(_Functor<Boolean> predicate) {
        setPredicate(predicate);
    }

    public While(_Functor<Boolean> predicate, _Functor<T> body) {
        setPredicate(predicate);
        setBody(body);
    }

    public While<T> setPredicate(_Functor<Boolean> predicate) {
        this.predicate = predicate;
        if (predicate != null)
            predicate.setOuter(this);
        return this;
    }

    public While<T> setBody(_Functor<T> body) {
        this.body = body;
        if (body != null)
            body.setOuter(this);
        return this;
    }

    @Override
    public T eval2() throws EvalException, Control {
        T last = null;

        if (predicate == null) {
            while (true) {
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                } catch (ControlContinue controlContinue) {
                    if (controlContinue.matches(this))
                        continue;
                    throw controlContinue;
                }
            }
        } else {
            while (predicate.evalBoolean()) {
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    break;
                } catch (ControlContinue controlContinue) {
                    continue;
                }
            }
        }
        return last;
    }

    @Override
    public While<T> reduce() throws FunctorException {
        if (predicate instanceof _Functor<?>) {
            _Functor<?> afPredicate = (_Functor<?>) predicate;
            if (body == null)
                if (afPredicate.isClosed())
                    return null;
            if (afPredicate.isEvaluated())
                try {
                    if (!afPredicate.evalBoolean())
                        return null;
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
        }
        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (!isValidated()) {
            if (predicate != null)
                predicate.validate();
            if (body != null)
                body.validate();
            setFlagBits(VALIDATED);
        }
    }

    private static ThreadLocal<While<?>> tlBuilt = new ThreadLocal<While<?>>();

    public static <U> While<U> build(_Functor<Boolean> predicate, _Functor<U> body) {
        While<U> built = built();
        tlBuilt.set(null);
        built.setPredicate(predicate);
        built.setBody(body);
        return built;
    }

    @SuppressWarnings("unchecked")
    public static <U> While<U> built() {
        While<U> built = (While<U>) tlBuilt.get();
        if (built == null) {
            built = new While<U>();
            tlBuilt.set(built);
        }
        return built;
    }

}
