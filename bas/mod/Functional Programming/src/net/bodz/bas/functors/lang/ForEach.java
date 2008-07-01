package net.bodz.bas.functors.lang;

import java.util.Collection;

import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;

public class ForEach<T> extends _Functor<T> {

    public class Index extends TransientConstant<Object> {
        @Override
        public Object eval2() {
            return index;
        }
    }

    protected _Functor<Collection<?>> coll;
    protected _Functor<T>             body;

    protected Object                 index;

    public ForEach() {
    }

    public ForEach(_Functor<Collection<?>> coll, _Functor<T> body) {
        setColl(coll);
        setBody(body);
    }

    public ForEach(_Functor<Collection<?>> coll) {
        this(coll, null);
    }

    public ForEach<T> setColl(_Functor<Collection<?>> coll) {
        this.coll = coll;
        if (coll != null)
            coll.setOuter(this);
        return this;
    }

    public ForEach<T> setBody(_Functor<T> body) {
        this.body = body;
        if (body != null)
            body.setOuter(this);
        return this;
    }

    public Index index() {
        return new Index();
    }

    @Override
    public T eval2() throws Control, EvalException {
        Collection<?> c = coll.eval();

        if (body == null)
            return null;

        T last = null;

        for (Object i : c) {
            index = i;
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

        return last;
    }

    @Override
    @SuppressWarnings("unchecked")
    public _Functor<?> reduce() throws FunctorException {
        if (isReduced())
            return this;

        if (body != null)
            body = (_Functor<T>) body.reduce();

        if (coll != null)
            coll = (_Functor<Collection<?>>) coll.reduce();
        if (coll instanceof _Functor) {
            _Functor<Collection<?>> afColl = (_Functor<Collection<?>>) coll;
            if (afColl.isEvaluated()) {
                try {
                    Collection<?> c = afColl.eval();
                    if (c.size() == 0)
                        return null;
                    // if (c.size() == 1)
                    // return body;
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
            }
            if (body == null)
                if (afColl.isClosed())
                    return null;
        }
        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (!isValidated()) {
            if (coll != null)
                coll.validate();
            if (body != null)
                body.validate();
            setFlagBits(VALIDATED);
        }
    }

    static ThreadLocal<ForEach<?>> tlBuilt = new ThreadLocal<ForEach<?>>();

    public static <U> ForEach<U> build(_Functor<Collection<?>> coll,
            _Functor<U> body) {
        ForEach<U> built = built();
        tlBuilt.set(null);
        built.setColl(coll);
        built.setBody(body);
        return built;
    }

    @SuppressWarnings("unchecked")
    public static <U> ForEach<U> built() {
        ForEach<U> built = (ForEach<U>) tlBuilt.get();
        if (built == null) {
            built = new ForEach<U>();
            tlBuilt.set(built);
        }
        return built;
    }

}
