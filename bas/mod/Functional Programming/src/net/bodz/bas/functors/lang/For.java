package net.bodz.bas.functors.lang;

import net.bodz.bas.functors.IFunctor;
import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;

public class For<T> extends _Functor<T> {

    protected IFunctor<?> initor;
    protected IFunctor<?> predicate;
    protected IFunctor<?> incrementor;
    protected IFunctor<?> body;

    public For() {
    }

    public For(_Functor<?> initor, _Functor<Boolean> predicate,
            _Functor<?> incrementor, _Functor<T> body) {
        setInitor(initor);
        setPredicate(predicate);
        setIncrementor(incrementor);
        setBody(body);
    }

    public For(_Functor<?> initor, _Functor<Boolean> predicate,
            _Functor<?> incrementor) {
        this(initor, predicate, incrementor, null);
    }

    public For<T> setInitor(_Functor<?> initor) {
        this.initor = initor;
        if (initor != null)
            initor.setOuter(this);
        return this;
    }

    public For<T> setPredicate(_Functor<Boolean> predicate) {
        this.predicate = predicate;
        if (predicate != null)
            predicate.setOuter(this);
        return this;
    }

    public For<T> setIncrementor(_Functor<?> incrementor) {
        this.incrementor = incrementor;
        if (incrementor != null)
            incrementor.setOuter(this);
        return this;
    }

    public For<T> setBody(_Functor<T> body) {
        this.body = body;
        if (body != null)
            body.setOuter(this);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T eval2() throws Control, EvalException {
        if (initor != null)
            initor.eval();

        Object last = null;
        while ((Boolean) predicate.eval()) {
            if (body != null)
                try {
                    last = body.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                } catch (ControlContinue controlContinue) {
                    if (controlContinue.matches(this)) {
                        incrementor.eval();
                        continue;
                    }
                    throw controlContinue;
                }
            incrementor.eval();
        }
        return (T) last;
    }

    @Override
    @SuppressWarnings("unchecked")
    public _Functor<?> reduce() throws FunctorException {
        if (isReduced())
            return this;

        if (body != null)
            body = (_Functor<?>) body.reduce();

        if (initor != null)
            initor = (_Functor<?>) initor.reduce();

        if (predicate != null)
            predicate = (_Functor<Boolean>) predicate.reduce();
        if (predicate instanceof _Functor) {
            _Functor<?> afPredicate = (_Functor<?>) predicate;
            if (afPredicate.isEvaluated()) {
                try {
                    if (!afPredicate.evalBoolean())
                        return (_Functor<?>) initor;
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
            }
        }

        if (incrementor != null)
            incrementor = incrementor.reduce();

        if (body == null)
            if (predicate instanceof _Functor && incrementor instanceof _Functor) {
                if (((_Functor<?>) predicate).isClosed()
                        && ((_Functor<?>) incrementor).isClosed())
                    return (_Functor<?>) initor;
            }
        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (!isValidated()) {
            if (initor != null)
                initor.validate();
            if (predicate != null)
                predicate.validate();
            if (incrementor != null)
                incrementor.validate();
            if (body != null)
                body.validate();
            setFlagBits(VALIDATED);
        }
    }

    static ThreadLocal<For<?>> tlBuilt = new ThreadLocal<For<?>>();

    public static <ValueType> For<ValueType> build(_Functor<?> initor,
            _Functor<Boolean> predicate, _Functor<?> incrementor,
            _Functor<ValueType> body) {
        For<ValueType> built = built();
        tlBuilt.set(null);
        built.setInitor(initor);
        built.setPredicate(predicate);
        built.setIncrementor(incrementor);
        built.setBody(body);
        return built;
    }

    @SuppressWarnings("unchecked")
    public static <U> For<U> built() {
        For<U> built = (For<U>) tlBuilt.get();
        if (built == null) {
            built = new For<U>();
            tlBuilt.set(built);
        }
        return built;
    }

}
