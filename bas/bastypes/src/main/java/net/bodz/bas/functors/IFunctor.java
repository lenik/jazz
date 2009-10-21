package net.bodz.bas.functors;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang2.EvalException;
import net.bodz.bas.lang2.Functor;
import net.bodz.bas.lang2.FunctorException;

public interface IFunctor<T> extends Functor<T> {

    T eval() throws EvalException, Control;

    /**
     * should reduce the children functors and internal component functors.
     * 
     * @param outer
     * @return
     * @throws FunctorException
     */
    IFunctor<?> reduce() throws FunctorException;

    /**
     * @throws ValidationException
     */
    void validate() throws ValidationException;

}
