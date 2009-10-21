package net.bodz.bas.functors.lang;

import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang2.EvalException;

/**
 * <pre>
 * {
 *     Functor paramName = new Variable(&quot;name&quot;);
 *     Functor paramAge = new Variable(&quot;age&quot;);
 *     Function myFunc1 = Function.build(&quot;myFunc1&quot;, &quot;name&quot;, &quot;age&quot;)
 *             .setBody(
 *                     new StringConcat(Function.built().parameter(&quot;name&quot;), Function.built()
 *                             .parameter(&quot;age&quot;)));
 *     Function myFunc2 = Function.build(&quot;myFunc2&quot;, 2).setBody(
 *             new StringConcat(Function.built().parameter(0), Function.built().parameter(1)));
 *     Function myFunc3 = new Function(&quot;myFunc3&quot;).setBody(new StringConcat(paramName, paramAge));
 * }
 * </pre>
 */
public class Function extends _Functor<Object> {

    public Function() {
    }

    public Function(String functionName) {
    }

    public Function(int parameters) {
    }

    public Function(String functionName, int parameters) {
    }

    public Function(String functionName, String... parameterNames) {
    }

    @Override
    public Object eval2() throws EvalException {
        return null;
    }

}
