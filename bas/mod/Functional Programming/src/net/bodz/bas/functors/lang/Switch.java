package net.bodz.bas.functors.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.functors.IFunctor;
import net.bodz.bas.functors.Sequence;
import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;
import net.bodz.bas.nls.FPNLS;
import net.bodz.bas.types.Key;

public class Switch<T> extends _Functor<T> {

    protected _Functor<Object>     key;
    protected List<_Functor<T>>    caseList = new ArrayList<_Functor<T>>();
    protected Map<Object, Integer> caseMap  = new HashMap<Object, Integer>();

    protected T                    evaluated;

    public static final Key        DEFAULT  = new Key("default"); //$NON-NLS-1$

    public Switch() {
    }

    public Switch(_Functor<Object> key) {
        setKey(key);
    }

    public Switch<T> setKey(_Functor<Object> key) {
        this.key = key;
        if (key != null)
            key.setOuter(this);
        return this;
    }

    public Switch<T> setCase(Object caseKey, _Functor<T> caseValue) {
        if (caseMap.containsKey(caseKey))
            throw new IllegalArgumentException(FPNLS.getString("Switch.1") + caseKey //$NON-NLS-1$
                    + FPNLS.getString("Switch.2")); //$NON-NLS-1$
        caseMap.put(caseKey, caseList.size());
        caseList.add(caseValue);
        return this;
    }

    @Override
    public T eval2() throws Control, EvalException {
        // Object key1 = key.eval();

        IFunctor<T> functor = null;

        Integer iFunctor = caseMap.get(key);
        if (iFunctor == null)
            iFunctor = caseMap.get(DEFAULT);

        if (iFunctor == null)
            return null;

        functor = caseList.get(iFunctor);

        T last = null;

        if (functor != null) {
            for (int i = iFunctor; i < caseList.size(); i++) {
                try {
                    last = functor.eval();
                } catch (ControlBreak controlBreak) {
                    if (controlBreak.matches(this))
                        break;
                    throw controlBreak;
                }
            }
        }

        return last;
    }

    @SuppressWarnings("unchecked")
    @Override
    public _Functor<T> reduce() throws FunctorException {
        if (key != null)
            key = (_Functor<Object>) key.reduce();
        if (key instanceof _Functor) {
            _Functor<Object> afKey = (_Functor<Object>) key;
            if (afKey.isEvaluated()) {
                try {
                    Object key1 = afKey.eval();
                    Integer i = caseMap.get(key1);
                    if (i == null)
                        i = caseMap.get(DEFAULT);
                    if (i == null)
                        return null;
                    if (i > 0) {
                        Sequence<T> seq = new Sequence<T>();
                        seq.addAll(caseList.subList(i, caseList.size() - 1));
                        return (_Functor<T>) seq.reduce();
                    }
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
            }
        }
        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (!isValidated()) {
            if (key != null)
                key.validate();
            for (int i = 0; i < caseList.size(); i++)
                caseList.get(i).validate();
            setFlagBits(VALIDATED);
        }
    }

}
